package com.music.musicPLayer.service;

import com.music.musicPLayer.data.model.FileModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileInterface {
    FileModel uploadVideo(String path, MultipartFile file) throws IOException;

    InputStream getResource(String path, String fileName , Long id) throws FileNotFoundException;
}
