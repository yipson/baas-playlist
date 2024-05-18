package com.quipux.baasplaylists.domain.usecase.port;

import com.quipux.baasplaylists.adapter.driving.rest.model.CreatePlaylistDto;
import com.quipux.baasplaylists.domain.model.Playlist;

import java.util.List;

public interface PlaylistPort {
    Playlist create(Playlist playlist);
    List<Playlist> getPlayLists();
}
