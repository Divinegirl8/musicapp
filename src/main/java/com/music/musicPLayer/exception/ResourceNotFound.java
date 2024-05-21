package com.music.musicPLayer.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceNotFound extends RuntimeException {

    private String errorCode ;
    private String errorMessage ;

}
