package dev9.lapco.service;

import dev9.lapco.request.VideoRequest;
import dev9.lapco.response.VideoResponse;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

    VideoResponse getAllVideo ();

    VideoResponse addVideo (MultipartFile file, String videoName);

    VideoResponse deleteVideo (VideoRequest video);

}
