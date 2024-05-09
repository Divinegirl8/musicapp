package com.music.musicPLayer.data.repository;

import com.music.musicPLayer.data.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListRepository extends JpaRepository<PlayList,Long> {
}
