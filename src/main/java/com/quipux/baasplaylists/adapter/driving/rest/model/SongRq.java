package com.quipux.baasplaylists.adapter.driving.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongRq {
    @JsonProperty("title")
    private String title;

    @JsonProperty("artist")
    private String artist;

    @JsonProperty("album")
    private String album;

    @JsonProperty("year")
    private String year;

    @JsonProperty("gender")
    private String gender;
}
