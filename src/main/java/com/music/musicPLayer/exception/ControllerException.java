package com.music.musicPLayer.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ControllerException extends RuntimeException {
    private String errorCode ;
    private String errorMessage ;

}
