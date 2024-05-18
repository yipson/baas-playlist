package com.quipux.baasplaylists.adapter.driving.rest.controller;

import com.quipux.baasplaylists.adapter.driving.rest.model.CreatePlaylistDto;

import com.quipux.baasplaylists.adapter.mapper.PlaylistMapper;
import com.quipux.baasplaylists.domain.model.Playlist;
import com.quipux.baasplaylists.domain.usecase.port.PlaylistPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/api/v1/lists")
public class PlaylistController {

    private final PlaylistPort playlistPort;

    public PlaylistController(PlaylistPort playlistPort) {
        this.playlistPort = playlistPort;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatePlaylistDto> createPlaylist(@RequestBody CreatePlaylistDto createPlaylistDto){
        if(createPlaylistDto.getName() == null || StringUtils.isEmpty(createPlaylistDto.getName())) {
            return ResponseEntity.badRequest().body(CreatePlaylistDto.builder().description("Error name must not be null").build());
        }
        Playlist playlist = this.createPlaylistBuildRequest(createPlaylistDto);
        return this.createPlaylistBuildResponse(playlist);
    }

    private Playlist createPlaylistBuildRequest(CreatePlaylistDto createPlaylistDto) {
        return PlaylistMapper.rqToDomain(createPlaylistDto);
    }

    private ResponseEntity<CreatePlaylistDto> createPlaylistBuildResponse(Playlist playlist) {
        playlist = playlistPort.create(playlist);
        CreatePlaylistDto createdPlaylist =  PlaylistMapper.domainToRs(playlist);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPlaylist.getId())
                .toUri();

        return ResponseEntity.created(location)
                .location(location)
                .body(createdPlaylist);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CreatePlaylistDto>> getPlayLists(){
        List<Playlist> playlists =  playlistPort.getPlayLists();
        return ResponseEntity.ok(playlists.stream()
                .map(PlaylistMapper::domainToRs)
                .collect(Collectors.toList()));
    }

}
