package com.quipux.baasplaylists.domain.usecase;

import com.quipux.baasplaylists.domain.model.Genres;
import com.quipux.baasplaylists.domain.model.Playlist;
import com.quipux.baasplaylists.domain.repository.GenreRepositoryPort;
import com.quipux.baasplaylists.domain.repository.PlayListRepositoryPort;
import com.quipux.baasplaylists.domain.usecase.port.PlaylistPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService implements PlaylistPort {

    private final PlayListRepositoryPort playListRepositoryPort;
    private final GenreRepositoryPort genreRepositoryPort;

    public PlaylistService(PlayListRepositoryPort playListRepositoryPort,
                           GenreRepositoryPort genreRepositoryPort) {
        this.playListRepositoryPort = playListRepositoryPort;
        this.genreRepositoryPort = genreRepositoryPort;
    }

    @Override
    public Playlist create(Playlist playlist) {
        return playListRepositoryPort.save(playlist);
    }

    @Override
    public List<Playlist> getPlayLists() {
        return playListRepositoryPort.findAll();
    }

    @Override
    public String getDescription(String listName) {
        return playListRepositoryPort.findByName(listName);
    }

    @Override
    public void delete(String listName) {
        playListRepositoryPort.delete(listName);
    }

    @Override
    public Genres getGenders() {
        return genreRepositoryPort.findAllGenres();
    }
}
