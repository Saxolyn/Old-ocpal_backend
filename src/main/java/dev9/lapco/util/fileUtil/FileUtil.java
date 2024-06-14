package dev9.lapco.util.fileUtil;

import dev9.lapco.constant.Constants;
import dev9.lapco.constant.Message;
import dev9.lapco.request.VideoRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class FileUtil implements Constants, Message {

    @Value("${application.video.storage}")
    private String videoStorageLocation;

    public List<String> getFileLocationList() {
        File directory = new File(videoStorageLocation);
        File[] files = directory.listFiles();
        if (files == null) {
            return List.of();
        }
        return Arrays.stream(files).toList().stream().map(File::getName).toList();
    }

    public byte[] readFile(String fileName) throws IOException {
        File file = new File(videoStorageLocation + File.separator + fileName);
        if (!file.exists()) {
            return null;

        }
        return Files.readAllBytes(Paths.get(file.getPath()));
    }

    public File getFile(String fileName) throws IOException {
        File file = new File(videoStorageLocation + File.separator + fileName);
        if (!file.exists()) {
            return null;
        }
        return file;
    }

    public String getDuration(String fileName) {
        File file = new File(videoStorageLocation + File.separator + fileName);
        Metadata metadata = new Metadata();
        try (InputStream inputStream = new FileInputStream(file)) {
            AutoDetectParser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler();
            ParseContext context = new ParseContext();

            parser.parse(inputStream, handler, metadata, context);

            String duration = metadata.get("xmpDM:duration");
            if (duration != null) {
                Long durationInSeconds = ((Double) (Math.ceil(Double.parseDouble(duration)))).longValue(); // Convert milliseconds to seconds
                return formatDuration(durationInSeconds);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MF0003;
        }
        return MF0003;

    }

    public String formatDuration(Long seconds) {
        if (seconds == null || seconds < 0) {
            return DEFAULT_DURATION_VIDEO;
        }
        Duration duration = Duration.ofSeconds(seconds);
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long secs = duration.toSecondsPart();

        return String.format(DEFAULT_DURATION_VIDEO_PATTERN, hours, minutes, secs);
    }

    public ResponseEntity<StreamingResponseBody> loadEntireMediaFile(String localMediaFilePath) throws IOException {
        Path filePath = Paths.get(localMediaFilePath);
        if (!filePath.toFile().exists()) {
            throw new FileNotFoundException("The media file does not exist.");
        }

        long fileSize = Files.size(filePath);
        long endPos;
        if (fileSize > 0L) {
            endPos = fileSize - 1;
        } else {
            endPos = 0L;
        }

        return loadPartialMediaFile(localMediaFilePath, 0, endPos);
    }

    public ResponseEntity<StreamingResponseBody> loadPartialMediaFile(String localMediaFilePath, String rangeValues) throws IOException {
        if (!StringUtils.hasText(rangeValues)) {
            System.out.println("Read all media file content.");
            return loadEntireMediaFile(localMediaFilePath);
        } else {
            long rangeStart = 0L;
            long rangeEnd = 0L;

            if (!StringUtils.hasText(localMediaFilePath)) {
                throw new IllegalArgumentException
                        ("The full path to the media file is NULL or empty.");
            }

            Path filePath = Paths.get(localMediaFilePath);
            if (!filePath.toFile().exists()) {
                throw new FileNotFoundException("The media file does not exist.");
            }

            long fileSize = Files.size(filePath);

            System.out.println("Read rang seeking value.");
            System.out.println("Rang values: [" + rangeValues + "]");

            int dashPos = rangeValues.indexOf("-");
            if (dashPos > 0 && dashPos <= (rangeValues.length() - 1)) {
                String[] rangesArr = rangeValues.split("-");

                if (rangesArr.length > 0) {
                    System.out.println("ArraySize: " + rangesArr.length);
                    if (StringUtils.hasText(rangesArr[0])) {
                        System.out.println("Rang values[0]: [" + rangesArr[0] + "]");
                        String valToParse = numericStringValue(rangesArr[0]);
                        rangeStart = safeParseStringValueToLong(valToParse, 0L);
                    }
                    if (rangesArr.length > 1) {
                        System.out.println("Rang values[1]: [" + rangesArr[1] + "]");
                        String valToParse = numericStringValue(rangesArr[1]);
                        rangeEnd = safeParseStringValueToLong(valToParse, 0L);
                    } else {
                        if (fileSize > 0) {
                            rangeEnd = fileSize - 1L;
                        }
                    }
                }
            }

            if (rangeEnd == 0L && fileSize > 0L) {
                rangeEnd = fileSize - 1;
            }
            if (fileSize < rangeEnd) {
                rangeEnd = fileSize - 1;
            }

            System.out.println(String.format("Parsed Range Values: [%d] - [%d]",
                    rangeStart, rangeEnd));

            return loadPartialMediaFile(localMediaFilePath, rangeStart, rangeEnd);
        }
    }

    public ResponseEntity<StreamingResponseBody> loadPartialMediaFile(String localMediaFilePath, long fileStartPos, long fileEndPos) throws IOException {
        StreamingResponseBody responseStream;
        Path filePath = Paths.get(localMediaFilePath);
        if (!filePath.toFile().exists()) {
            throw new FileNotFoundException("The media file does not exist.");
        }

        long fileSize = Files.size(filePath);
        if (fileStartPos < 0L) {
            fileStartPos = 0L;
        }

        if (fileSize > 0L) {
            if (fileStartPos >= fileSize) {
                fileStartPos = fileSize - 1L;
            }

            if (fileEndPos >= fileSize) {
                fileEndPos = fileSize - 1L;
            }
        } else {
            fileStartPos = 0L;
            fileEndPos = 0L;
        }

        byte[] buffer = new byte[1024];
        String mimeType = Files.probeContentType(filePath);

        final HttpHeaders responseHeaders = new HttpHeaders();
        String contentLength = String.valueOf((fileEndPos - fileStartPos) + 1);
        responseHeaders.add("Content-Type", mimeType);
        responseHeaders.add("Content-Length", contentLength);
        responseHeaders.add("Accept-Ranges", "bytes");
        responseHeaders.add("Content-Range",
                String.format("bytes %d-%d/%d", fileStartPos, fileEndPos, fileSize));

        final long fileStartPos2 = fileStartPos;
        final long fileEndPos2 = fileEndPos;
        responseStream = os -> {
            RandomAccessFile file = new RandomAccessFile(localMediaFilePath, "r");
            try (file) {
                long pos = fileStartPos2;
                file.seek(pos);
                while (pos < fileEndPos2) {
                    file.read(buffer);
                    os.write(buffer);
                    pos += buffer.length;
                }
                os.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        return new ResponseEntity<StreamingResponseBody>
                (responseStream, responseHeaders, HttpStatus.PARTIAL_CONTENT);
    }

    private String numericStringValue(String origVal) {
        String retVal = "";
        if (StringUtils.hasText(origVal)) {
            retVal = origVal.replaceAll("[^0-9]", "");
            System.out.println("Parsed Long Int Value: [" + retVal + "]");
        }

        return retVal;
    }

    private long safeParseStringValueToLong(String valToParse, long defaultVal) {
        long retVal = defaultVal;
        if (StringUtils.hasText(valToParse)) {
            try {
                retVal = Long.parseLong(valToParse);
            } catch (NumberFormatException ex) {
                // TODO: log the invalid long int val in text format.
            }
        }

        return retVal;
    }

    public Optional<String> saveVideo(MultipartFile file, String fileName) {
    try {
            if (Strings.isBlank(fileName)) {
                fileName = file.getName();
            }
        file.transferTo( Paths.get(videoStorageLocation + File.separator + fileName));

    } catch (Exception e) {
            log.error(MF0004);
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(getDuration(fileName));
    }

    public Optional<String> getVideoPath(String fileName){
        try{
            return Optional.of(Paths.get(videoStorageLocation + File.separator + fileName).toString());
        }catch (Exception e){
            log.error(MF0005);
            e.printStackTrace();
            return Optional.empty();
        }

    }

    public boolean isDeleteVideo(VideoRequest video) {
        try{
            if(!Strings.isBlank(video.getVideoPath())
                  && !Objects.equals(video.getVideoName(),video.getVideoPath())){
                throw new Exception(MF0006);
            }
            File file = new File(video.getVideoPath());

//            if(file.getName())

            return Files.deleteIfExists(file.toPath());
        } catch (Exception e){
            log.info(MF0006);
            e.printStackTrace();
            return false;
        }


    }

//    public String convertPathSeperator(String path){
//        return path.replace()
//    }

}
