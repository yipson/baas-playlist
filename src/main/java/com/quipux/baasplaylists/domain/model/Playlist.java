package com.quipux.baasplaylists.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {
    private Long id;
    private String name;
    private String description;
    private List<Song> songs;

}
