package dev9.lapco.controller;

import dev9.lapco.request.VideoRequest;
import dev9.lapco.response.VideoResponse;
import dev9.lapco.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(value ="/video")
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/all")
    public VideoResponse getAllVideo (){
        return videoService.getAllVideo();
    }

    @PostMapping("/add")
    public VideoResponse addVideo (@RequestPart ("file") MultipartFile file, @RequestParam ("video_name") String videoName){
        return videoService.addVideo(file,videoName);
    }

    @DeleteMapping("/delete-video")
    public VideoResponse deleteVideo (@RequestBody VideoRequest video){
        return videoService.deleteVideo(video);
    }

}
