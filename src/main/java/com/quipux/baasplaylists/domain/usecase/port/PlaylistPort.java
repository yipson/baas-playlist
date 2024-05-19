package com.quipux.baasplaylists.domain.usecase.port;

import com.quipux.baasplaylists.domain.model.Genres;
import com.quipux.baasplaylists.domain.model.Playlist;

import java.util.List;

public interface PlaylistPort {
    Playlist create(Playlist playlist);
    List<Playlist> getPlayLists();
    String getDescription(String listName);
    void delete(String listName);
    Genres getGenders();
}
