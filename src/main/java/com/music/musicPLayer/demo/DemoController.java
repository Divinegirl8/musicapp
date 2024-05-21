package com.music.musicPLayer.demo;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.music.musicPLayer.config.UpdateModel;
import com.music.musicPLayer.data.model.FileModel;
import com.music.musicPLayer.data.model.Video;
import com.music.musicPLayer.data.repository.VideoRepository;
import com.music.musicPLayer.exception.ControllerException;
import com.music.musicPLayer.exception.ResourceNotFound;
import com.music.musicPLayer.service.FileInterface;
import com.music.musicPLayer.service.VideoInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/demo-controller")
@RequiredArgsConstructor
public class DemoController {
//    private final Cloudinary cloudinary;

    private final VideoInterface service;
    private final FileInterface fileSevice;
    private final VideoRepository videoRepository;


    @Value("${project.video}")
    private String path;
    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("hello from secured endpoint");
    }

    @GetMapping("/hi")
    public ResponseEntity<String> sayHi(){
        return ResponseEntity.ok("hi from secured endpoint");
    }


    @PostMapping("/save")
    public ResponseEntity<?> saveVideo(@RequestBody Video video) {
        try {
            Video saveVideos = service.createPost(video);
            return new ResponseEntity<Video>(saveVideos, HttpStatus.OK);
        } catch (ResourceNotFound e) {
            ControllerException controllerException = new ControllerException(e.getErrorCode(),
                    e.getErrorMessage() + e.getMessage());
            return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
        }
    }

    //1. get Video by id ;
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getDataById(@PathVariable Long id) {
        try {
            Video video = service.getVideosById(id);
            return new ResponseEntity<Video>(video, HttpStatus.CREATED);

        } catch (ResourceNotFound e) {
            ControllerException controllerException = new ControllerException(e.getErrorCode(),
                    e.getErrorMessage() + e.getMessage());
            return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ControllerException controllerException = new ControllerException("504", "id not found");
            return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
        }
    }

    //2. Get List Of videos .
    @SuppressWarnings("unused")
    @GetMapping("/all")
    public ResponseEntity<?> getListofData() {
        Video videos = new Video();
        try {
            List<Video> v = service.getAllVideos();
            return new ResponseEntity<List<Video>>(v, HttpStatus.CREATED);
        } catch (Exception e) {
            ControllerException controllerException = new ControllerException("404", "Empty database is found");
            return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
        }
    }

    //3. Posting Video api .
    @PostMapping("/upload/{id}")
    public ResponseEntity<Video> uploadVideo(@RequestParam("video") MultipartFile video, @PathVariable Long id)
            throws IOException {
        Video v = this.service.getVideosById(id);
        FileModel fileModel = this.fileSevice.uploadVideo(path, video);
        v.setVideoName(fileModel.getVideoFileName());
        Video uploadVideo = this.service.updatePost(v, id);
        return new ResponseEntity<Video>(uploadVideo, HttpStatus.OK);
    }

    //4.To play video .
    @GetMapping(value = "/play/{id}", produces = MediaType.ALL_VALUE)
    public void downloadImage(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Optional<Video> video = videoRepository.findById(id);
        InputStream resource = this.fileSevice.getResource(path, video.get().getVideoName(), id);
        response.setContentType(MediaType.ALL_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

    //5. Delete videos by id .
    @DeleteMapping("/{id}")
    public String deleteVideo(@PathVariable Long id) throws IOException {
        Optional<Video> video = videoRepository.findById(id);
        Path exactPath = Paths.get(path + File.separator + video.get().getVideoName());
        System.out.println(exactPath);
        try {
            Files.deleteIfExists(exactPath);

        } catch (Exception e1) {
            e1.getMessage();
            System.out.println(e1.getMessage()+"can not delete now ");
        }
        this.service.deleteVideos(id);

        return "video deleted successfully";
    }

    //Update DataModel of Video .
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateModel> setVideoData(@RequestBody UpdateModel updateModel, @PathVariable Long id){
        try {
            service.updateModel(updateModel, id);
            return new ResponseEntity<UpdateModel>(updateModel, HttpStatus.OK);
        }catch(Exception e) {
            throw new ResourceNotFound("404","user id not found");
        }
    }



//    @PostMapping("/upload")
//    public String uploadFile(@RequestParam("file") MultipartFile file) {
//        if (file.isEmpty()) {
//            return "File is empty";
//        }
//
//        try {
//            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
//                    ObjectUtils.emptyMap());
//
//            return "File uploaded successfully: " + uploadResult.get("url");
//        } catch (IOException e) {
//
//            return "File upload failed: " + e.getMessage();
//        }
//    }
}


//java.lang.IllegalStateException: No primary or single unique constructor found for interface javax.servlet.http.HttpServletResponse