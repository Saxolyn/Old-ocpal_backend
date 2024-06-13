package dev9.lapco.service;

import dev9.lapco.request.VideoRequest;
import dev9.lapco.response.VideoResponse;

public interface VideoService {

    VideoResponse getAllVideo ();

    VideoResponse addVideo (VideoRequest video);

    VideoResponse deleteVideo (VideoRequest video);

    VideoResponse assignVideo (VideoRequest video);
}
