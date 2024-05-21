package com.music.musicPLayer.data.repository;

import com.music.musicPLayer.data.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video,Long> {
}
