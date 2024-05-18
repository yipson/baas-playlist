package com.quipux.baasplaylists.domain.repository;

import com.quipux.baasplaylists.adapter.driving.rest.model.CreatePlaylistDto;
import com.quipux.baasplaylists.domain.model.Playlist;

import java.util.List;

public interface PlayListRepositoryPort {
    Playlist save(Playlist playlist);
    List<Playlist> findAll();
    String findByName(String listName);
    void delete(String listName);
}
