package com.quipux.baasplaylists.domain.repository;

import com.quipux.baasplaylists.domain.model.Playlist;

public interface PlayListRepositoryPort {
    Playlist save(Playlist playlist);
}
