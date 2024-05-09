package com.music.musicPLayer.data.repository;

import com.music.musicPLayer.data.model.Artiste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtisteRepository extends JpaRepository<Artiste,Long> {
}
