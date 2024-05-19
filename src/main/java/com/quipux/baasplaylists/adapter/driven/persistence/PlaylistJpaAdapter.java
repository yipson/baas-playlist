package com.quipux.baasplaylists.adapter.driven.persistence;

import com.quipux.baasplaylists.adapter.driven.persistence.entity.PlaylistEntity;
import com.quipux.baasplaylists.adapter.mapper.PlaylistMapper;
import com.quipux.baasplaylists.domain.model.Playlist;
import com.quipux.baasplaylists.domain.repository.PlayListRepositoryPort;
import com.quipux.baasplaylists.utils.exception.DuplicateRecordException;
import com.quipux.baasplaylists.utils.exception.PlayListNotFoundException;
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
        if(playlistJpaRepository.countByName(playlist.getName()) > 0) {
            throw new DuplicateRecordException("This playlist name already exist");
        }
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

    @Override
    public String findByName(String listName) {
        return playlistJpaRepository.findDescriptionByName(listName)
                .orElseThrow(() -> new PlayListNotFoundException("The playlist was not found"));
    }

    @Override
    public void delete(String listName) {
        PlaylistEntity entity = playlistJpaRepository.findByName(listName)
                .orElseThrow(() -> new PlayListNotFoundException("The playlist was not found"));
        playlistJpaRepository.delete(entity);
    }
}
