package dev9.lapco.service.impl;

import dev9.lapco.constant.Message;
import dev9.lapco.constant.StatusCode;
import dev9.lapco.entity.AccountEntity;
import dev9.lapco.entity.VideoEntity;
import dev9.lapco.repository.VideoRepository;
import dev9.lapco.request.VideoRequest;
import dev9.lapco.response.VideoResponse;
import dev9.lapco.service.VideoService;
import dev9.lapco.util.ValidateUtil;
import dev9.lapco.util.fileUtil.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService, Message, StatusCode {

    private final FileUtil fileUtil;

    private final VideoRepository videoRepository;

    @Override
    public VideoResponse getAllVideo() {
       if(ValidateUtil.isValidAuthority()){
           return VideoResponse.builder().status(UNAUTHORIZED).message(ME0001).errorCode(true).videos(new ArrayList<>()).build();
        }
       //TODO : not had paginator yet
       return VideoResponse.builder().status(SUCCESS).message(MI0006).errorCode(false).videos(videoRepository.findAll()).build();
    }

    @Override
    public VideoResponse addVideo(MultipartFile file, String videoName) {
        if(ValidateUtil.isValidAuthority()){
            return VideoResponse.builder().status(UNAUTHORIZED).message(ME0001).errorCode(true).videos(new ArrayList<>()).build();
        }

        if(videoRepository.exists(Example.of(VideoEntity.builder().videoName(videoName).build()))){
            return VideoResponse.builder().status(BAD_REQUEST).message(ME0012).errorCode(true).videos(new ArrayList<>()).build();
        }

        if(file.isEmpty()){
            return VideoResponse.builder().status(BAD_REQUEST).message(ME0011).errorCode(true).videos(new ArrayList<>()).build();
        }
        try{
            String duration = fileUtil.saveVideo(file, videoName).orElseThrow();
            String path = fileUtil.getVideoPath(videoName).orElseThrow();
            return VideoResponse.builder()
                    .status(SUCCESS)
                    .message(MI0007)
                    .errorCode(false)
                    .videos(List.of(videoRepository.save(VideoEntity.builder()
                            .videoName(videoName)
                            .videoCode(null)
                            .duration(duration)
                            .path(path)
                            .build())))
                    .build();
        }catch(Exception e){
            return VideoResponse.builder()
                    .status(BAD_REQUEST)
                    .message(MF0004)
                    .errorCode(false)
                    .videos(new ArrayList<>())
                    .build();
        }
        // lack video code generate logic


    }

    @Override
    public VideoResponse deleteVideo(VideoRequest video) {
        if(ValidateUtil.isValidAuthority()){
            return VideoResponse.builder().status(UNAUTHORIZED).message(ME0001).errorCode(true).videos(new ArrayList<>()).build();
        }

        Optional<VideoEntity> checkedVideoEntity =  videoRepository.findOne(Example.of(VideoEntity.builder()
                .videoName(video.getVideoName()).build()));

        if(checkedVideoEntity.isEmpty()){
            return VideoResponse.builder().status(BAD_REQUEST).message(ME0013).errorCode(true).videos(new ArrayList<>()).build();
        }

        if(fileUtil.isDeleteVideo(video)){
            videoRepository.delete(checkedVideoEntity.get());
            return VideoResponse.builder()
                    .status(SUCCESS)
                    .message(MI0008)
                    .errorCode(false)
                    .videos(List.of(checkedVideoEntity.get()))
                    .build();
        }
        return VideoResponse.builder().status(INTERNAL_SERVER_ERROR).message(MF0006).errorCode(true).videos(new ArrayList<>()).build();
    }

}
