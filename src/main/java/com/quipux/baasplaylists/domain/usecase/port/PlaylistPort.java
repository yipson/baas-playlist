package com.quipux.baasplaylists.domain.usecase.port;

import com.quipux.baasplaylists.domain.model.Playlist;

public interface PlaylistPort {
    Playlist create(Playlist playlist);
}
