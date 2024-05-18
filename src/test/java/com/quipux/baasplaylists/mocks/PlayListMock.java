package com.quipux.baasplaylists.mocks;

import com.quipux.baasplaylists.adapter.driven.persistence.entity.PlaylistEntity;
import com.quipux.baasplaylists.adapter.driven.persistence.entity.SongEntity;
import com.quipux.baasplaylists.adapter.driving.rest.model.CreatePlaylistDto;
import com.quipux.baasplaylists.adapter.driving.rest.model.SongRq;
import com.quipux.baasplaylists.domain.model.Playlist;
import com.quipux.baasplaylists.domain.model.Song;

import java.util.Arrays;

public class PlayListMock {

    public static Playlist buildPlaylist(String name) {
        return Playlist.builder()
                .id(1L)
                .name(name)
                .description("Description 1")
                .songs(Arrays.asList(
                        buildSong(),
                        buildSong()))
                .build();
    }

    public static Song buildSong() {
        return Song.builder()
                .title("Song 1")
                .artist("Artist 1")
                .album("Album 1")
                .year("2021")
                .gender("Pop")
                .build();
    }

    public static CreatePlaylistDto buildCreatePlaylistDto(String name) {
        return CreatePlaylistDto.builder()
                .name(name)
                .description("Description 1")
                .songsRq(Arrays.asList(
                        buildSongRq(),
                        buildSongRq()))
                .build();
    }

    public static SongRq buildSongRq() {
        return SongRq.builder()
                .title("Song 1")
                .artist("Artist 1")
                .album("Album 1")
                .year("2021")
                .gender("Pop")
                .build();
    }


    public static PlaylistEntity buildPlaylistEntity(Long id, String name) {
        return PlaylistEntity.builder()
                .id(id)
                .name(name)
                .description("Description 1")
                .songs(Arrays.asList(
                        buildSongEntity(),
                        buildSongEntity()))
                .build();
    }

    public static SongEntity buildSongEntity() {
        return SongEntity.builder()
                .title("Song 1")
                .artist("Artist 1")
                .album("Album 1")
                .year("2021")
                .gender("Pop")
                .build();
    }
}
