package dev9.lapco.controller;

import dev9.lapco.request.VideoRequest;
import dev9.lapco.response.VideoResponse;
import dev9.lapco.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController("/video")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/get-all")
    public VideoResponse getAllVideo (){
        return videoService.getAllVideo();
    }

    @PutMapping("/add")
    public VideoResponse addVideo (@RequestBody VideoRequest video){
        return videoService.addVideo(video);
    }

    @DeleteMapping("/delete-video")
    public VideoResponse deleteVideo (@RequestBody VideoRequest video){
        return videoService.deleteVideo(video);
    }

    @PutMapping("/assign-video")
    public VideoResponse assignVideo (@RequestBody VideoRequest video) {
        return videoService.assignVideo(video);
    }

}
