package com.quipux.baasplaylists.adapter.driving.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlaylistDto {
    @JsonProperty("id")
    private Long id;

    @NotNull(message = "The field 'name' cannot be null")
    @NotEmpty(message = "The field 'name' cannot be null")
    @NotBlank(message = "The field 'name' cannot be null")
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @NotNull(message = "The field 'songs' cannot be null")
    @JsonProperty("songs")
    private List<SongRq> songsRq;
}
