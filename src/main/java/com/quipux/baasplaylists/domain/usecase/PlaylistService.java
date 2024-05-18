package com.quipux.baasplaylists.domain.usecase;

import com.quipux.baasplaylists.adapter.driving.rest.model.CreatePlaylistDto;
import com.quipux.baasplaylists.domain.model.Playlist;
import com.quipux.baasplaylists.domain.repository.PlayListRepositoryPort;
import com.quipux.baasplaylists.domain.usecase.port.PlaylistPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService implements PlaylistPort {

    private final PlayListRepositoryPort playListRepositoryPort;

    public PlaylistService(PlayListRepositoryPort playListRepositoryPort) {
        this.playListRepositoryPort = playListRepositoryPort;
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
}
