package com.quipux.baasplaylists.adapter.driven.persistence;

import com.quipux.baasplaylists.adapter.driven.persistence.entity.PlaylistEntity;
import com.quipux.baasplaylists.adapter.driving.rest.model.CreatePlaylistDto;
import com.quipux.baasplaylists.adapter.mapper.PlaylistMapper;
import com.quipux.baasplaylists.domain.model.Playlist;
import com.quipux.baasplaylists.domain.repository.PlayListRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaylistJpaAdapter implements PlayListRepositoryPort {

    private final PlaylistJpaRepository playlistJpaRepository;

    public PlaylistJpaAdapter(PlaylistJpaRepository playlistJpaRepository) {
        this.playlistJpaRepository = playlistJpaRepository;
    }


    @Override
    public Playlist save(Playlist playlist) {
        PlaylistEntity playlistEntity = PlaylistMapper.domainToEntity(playlist);
        playlistEntity = playlistJpaRepository.save(playlistEntity);
        return PlaylistMapper.entityToDomain(playlistEntity);
    }

    @Override
    public List<Playlist> findAll() {
        return playlistJpaRepository.findAll().stream()
                .map(PlaylistMapper::entityToDomain)
                .collect(Collectors.toList());
    }
}
