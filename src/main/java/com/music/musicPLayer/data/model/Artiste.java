package com.music.musicPLayer.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Artiste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 20, message ="The length of name cannot be more than 20")
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Song> songs = new ArrayList<>();
    private String bio;
}
