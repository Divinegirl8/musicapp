package com.music.musicPLayer.data.repository;

import com.music.musicPLayer.data.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song,Long> {
}
