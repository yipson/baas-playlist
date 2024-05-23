package com.quipux.baasplaylists.domain.usecase;

import com.quipux.baasplaylists.domain.model.Playlist;
import com.quipux.baasplaylists.domain.repository.PlayListRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlaylistServiceTest {

    @Mock
    private PlayListRepositoryPort playListRepositoryPort;

    @InjectMocks
    private PlaylistService playlistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Playlist playlist = new Playlist();
        Mockito.when(playListRepositoryPort.save(Mockito.any(Playlist.class))).thenReturn(playlist);

        Playlist result = playlistService.create(playlist);

        assertNotNull(result);
        assertEquals(playlist, result);
        Mockito.verify(playListRepositoryPort, Mockito.times(1)).save(playlist);
    }

    @Test
    void testGetPlayLists() {
        Playlist playlist1 = new Playlist();
        Playlist playlist2 = new Playlist();
        List<Playlist> playlists = Arrays.asList(playlist1, playlist2);
        Mockito.when(playListRepositoryPort.findAll()).thenReturn(playlists);

        List<Playlist> result = playlistService.getPlayLists();

        assertNotNull(result);
        assertEquals(2, result.size());
        Mockito.verify(playListRepositoryPort, Mockito.times(1)).findAll();
    }

    @Test
    void testGetDescription() {
        String listName = "My Playlist";
        String description = "Description of My Playlist";
        Mockito.when(playListRepositoryPort.findByName(listName)).thenReturn(description);

        String result = playlistService.getDescription(listName);

        assertNotNull(result);
        assertEquals(description, result);
        Mockito.verify(playListRepositoryPort, Mockito.times(1)).findByName(listName);
    }

    @Test
    void testDelete() {
        String listName = "MyPlaylist";
        Mockito.doNothing().when(playListRepositoryPort).delete(listName);

        playlistService.delete(listName);

        Mockito.verify(playListRepositoryPort, Mockito.times(1)).delete(listName);
    }
}