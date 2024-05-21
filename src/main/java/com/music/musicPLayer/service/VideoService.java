package com.music.musicPLayer.service;

import com.music.musicPLayer.config.UpdateModel;
import com.music.musicPLayer.data.model.Video;
import com.music.musicPLayer.data.repository.VideoRepository;
import com.music.musicPLayer.exception.ResourceNotFound;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class VideoService implements VideoInterface {

    private final VideoRepository videoRepository ;


    @Override
    public Video createPost(Video videos) {
        if(videos.getTitle().isEmpty()) {
            throw new ResourceNotFound("402 ","please field required details");
        }
        try {
            Video saveVideo = videoRepository.save(videos);
            videos.setAddedDate(new Date());
            videos.setVideoName("default.mp4");
            return videoRepository.save(saveVideo);
        }catch(IllegalArgumentException i) {
            throw new ResourceNotFound("401 ","hey your data is Empty");
        }catch(Exception e) {
            throw new ResourceNotFound("401"," something is wrong"+e.getMessage());
        }
    }

    @Override
    public Video getVideosById(Long id) {
        return this.videoRepository.findById(id).orElseThrow(() -> new ResourceNotFound("504"," id is not present"));
    }

    @Override
    public List<Video> getAllVideos() {
        List<Video> listOfVideo  = null ;
        try {
            listOfVideo = this.videoRepository.findAll();
            return listOfVideo ;
        }catch(Exception e) {
            throw new ResourceNotFound("404", "i am sorry "+e.getMessage());
        }
    }

    @Override
    public Video updatePost(Video videos, Long id) {
        Video video = this.videoRepository.findById(id).orElseThrow(()-> new ResourceNotFound("501 ", "Id not found"));

        video.setTitle(videos.getTitle());
        video.setDescription(videos.getDescription());
        video.setTags(videos.getTags());
        video.setAddedDate(new Date());
        return this.videoRepository.save(video);
    }

    @Override
    public void deleteVideos(Long id) {
        Video video = this.videoRepository.findById(id).orElseThrow(()-> new ResourceNotFound("403"," video id not found"));
        this.videoRepository.delete(video);
    }

    @Override
    public UpdateModel updateModel(UpdateModel updateModel, Long id) {
        Video video = this.videoRepository.findById(id).orElseThrow(()-> new ResourceNotFound("501" ," Id not found"));
        updateModel.setId(id);
        video.setTitle(updateModel.getTitle());
        video.setDescription(updateModel.getDescription());
        video.setTags(updateModel.getTags());
        video.setAddedDate(new Date());
        this.videoRepository.save(video);
        return updateModel ;
    }

}
