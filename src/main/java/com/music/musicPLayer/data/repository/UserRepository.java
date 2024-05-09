package com.music.musicPLayer.data.repository;

import com.music.musicPLayer.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
