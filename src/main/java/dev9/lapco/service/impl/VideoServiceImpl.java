package dev9.lapco.service.impl;

import dev9.lapco.constant.Message;
import dev9.lapco.constant.StatusCode;
import dev9.lapco.entity.VideoEntity;
import dev9.lapco.repository.VideoRepository;
import dev9.lapco.request.VideoRequest;
import dev9.lapco.response.VideoResponse;
import dev9.lapco.service.VideoService;
import dev9.lapco.util.fileUtil.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService, Message, StatusCode {

    private final FileUtil fileUtil;

    private final VideoRepository videoRepository;

    @Override
    public VideoResponse getAllVideo() {
        return null;
    }

    @Override
    public VideoResponse addVideo(VideoRequest video) {
        if(video.getVideoFile().isEmpty()){
            return VideoResponse.builder().status(BAD_REQUEST).message(ME0011).errorCode(true).videos(new ArrayList<>()).build();
        }

        if(videoRepository.exists(Example.of(VideoEntity.builder().videoName(video.getVideoName()).build()))){
            return VideoResponse.builder().status(BAD_REQUEST).message(ME0012).errorCode(true).videos(new ArrayList<>()).build();
        }

        // lack video code generate logic
        return VideoResponse.builder()
                .status(SUCCESS)
                .message(MI0007)
                .errorCode(false)
                .videos(List.of(videoRepository.save(VideoEntity.builder()
                    .videoName(video.getVideoName())
                    .videoCode(null)
                    .duration(fileUtil.saveVideo(video.getVideoFile(), video.getVideoName()))
                    .path(fileUtil.getVideoPath(video.getVideoName()))
                    .build())))
                .build();

    }

    @Override
    public VideoResponse deleteVideo(VideoRequest video) {
        return null;
    }

    @Override
    public VideoResponse assignVideo(VideoRequest video) {
        return null;
    }
}
