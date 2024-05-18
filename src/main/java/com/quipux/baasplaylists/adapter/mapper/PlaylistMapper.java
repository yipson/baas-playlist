package com.quipux.baasplaylists.adapter.mapper;

import com.quipux.baasplaylists.adapter.driven.persistence.entity.PlaylistEntity;
import com.quipux.baasplaylists.adapter.driven.persistence.entity.SongEntity;
import com.quipux.baasplaylists.adapter.driving.rest.model.CreatePlaylistDto;
import com.quipux.baasplaylists.adapter.driving.rest.model.SongRq;
import com.quipux.baasplaylists.domain.model.Playlist;
import com.quipux.baasplaylists.domain.model.Song;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaylistMapper {

    public static Playlist rqToDomain(CreatePlaylistDto dto) {
         Playlist playlist = new Playlist();
         playlist.setName(dto.getName());
         playlist.setDescription(dto.getDescription());
         playlist.setSongs(mapSongsRqToSongs(dto.getSongsRq()));
        return playlist;
    }

    public static List<Song> mapSongsRqToSongs(List<SongRq> rqList) {
        return rqList.stream()
                .map(rq -> Song.builder()
                        .title(rq.getTitle())
                        .artist(rq.getArtist())
                        .album(rq.getAlbum())
                        .year(rq.getYear())
                        .gender(rq.getGender())
                        .build())
                .collect(Collectors.toList());
    }

    public static CreatePlaylistDto domainToRs(Playlist domain) {
        return CreatePlaylistDto.builder()
                .id(domain.getId())
                .name(domain.getName())
                .description(domain.getDescription())
                .songsRq(mapSongsToSongsRq(domain.getSongs()))
                .build();
    }

    public static List<SongRq> mapSongsToSongsRq(List<Song> songs) {
        return songs.stream()
                .map(song -> SongRq.builder()
                        .title(song.getTitle())
                        .artist(song.getArtist())
                        .album(song.getAlbum())
                        .year(song.getYear())
                        .gender(song.getGender())
                        .build())
                .collect(Collectors.toList());
    }

    public static PlaylistEntity domainToEntity(Playlist domain) {
        return PlaylistEntity.builder()
                .name(domain.getName())
                .description(domain.getDescription())
                .songs(songDomainToEntity(domain.getSongs()))
                .build();
    }

    public static List<SongEntity> songDomainToEntity(List<Song> songs) {
        return songs.stream()
                .map(song -> SongEntity.builder()
                        .title(song.getTitle())
                        .artist(song.getArtist())
                        .album(song.getAlbum())
                        .year(song.getYear())
                        .gender(song.getGender())
                        .build())
                .collect(Collectors.toList());
    }

    public static Playlist entityToDomain(PlaylistEntity entity) {
        return Playlist.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .songs(songEntityToDomain(entity.getSongs()))
                .build();
    }

    public static List<Song> songEntityToDomain(List<SongEntity> songs) {
        return songs.stream()
                .map(song -> Song.builder()
                        .title(song.getTitle())
                        .artist(song.getArtist())
                        .album(song.getAlbum())
                        .year(song.getYear())
                        .gender(song.getGender())
                        .build())
                .collect(Collectors.toList());
    }
}
