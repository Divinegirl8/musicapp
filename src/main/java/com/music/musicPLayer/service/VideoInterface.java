package com.music.musicPLayer.service;

import com.music.musicPLayer.config.UpdateModel;
import com.music.musicPLayer.data.model.Video;

import java.util.List;

public interface VideoInterface {
    Video createPost(Video videos);

   Video getVideosById( Long id);

    List<Video> getAllVideos();

    Video updatePost(Video videos , Long id);

    void deleteVideos(Long id);
    UpdateModel updateModel(UpdateModel updateModel, Long id);

}
