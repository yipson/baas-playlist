package com.quipux.baasplaylists.domain.repository;

import com.quipux.baasplaylists.domain.model.Genres;

public interface GenreRepositoryPort {
    Genres findAllGenres();
}
