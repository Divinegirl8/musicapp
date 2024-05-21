package com.music.musicPLayer.implementation;

import com.music.musicPLayer.data.model.FileModel;
import com.music.musicPLayer.service.FileInterface;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceApp implements FileInterface {
    @Override
    public FileModel uploadVideo(String path, MultipartFile file) throws IOException {
        FileModel fileModel = new FileModel();
        //Fetch file original name .
        String fileName = file.getOriginalFilename();
        //try to generate random file name .
        String randomId = UUID.randomUUID().toString();
        String finalName = randomId.concat(fileName.substring(fileName.indexOf(".")));

        //File full path .
        String filePath = path + File.separator + finalName ;

        //Create folder to store file .you can create any where you want .
        File f = new File(path);
        if(!f.exists()) {
            f.mkdir();
        }

        Files.copy(file.getInputStream(),Paths.get(filePath));

        fileModel.setVideoFileName(finalName);

        return fileModel;
    }



    @Override
    public InputStream getResource(String path, String fileName, Long id) throws  FileNotFoundException {
        String fullPath = path+File.separator+fileName ;
        return new FileInputStream(fullPath);
    }
}
