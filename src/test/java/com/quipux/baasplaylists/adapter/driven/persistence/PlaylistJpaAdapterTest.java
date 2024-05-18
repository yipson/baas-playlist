package com.quipux.baasplaylists.adapter.driven.persistence;

import com.quipux.baasplaylists.adapter.driven.persistence.entity.PlaylistEntity;
//import com.quipux.baasplaylists.adapter.driven.persistence.repository.PlaylistJpaRepository;
//import com.quipux.baasplaylists.adapter.mapper.PlaylistMapper;
import com.quipux.baasplaylists.domain.model.Playlist;
import com.quipux.baasplaylists.mocks.PlayListMock;
import com.quipux.baasplaylists.utils.DuplicateRecordException;
import com.quipux.baasplaylists.utils.PlayListNotFoundException;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlaylistJpaAdapterTest {

    @Mock
    private PlaylistJpaRepository playlistJpaRepository;

    @InjectMocks
    private PlaylistJpaAdapter playlistJpaAdapter;

//    @BeforeEach()
//    void setup() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void testSave() {
        // Given
        Playlist playlist = PlayListMock.buildPlaylist("name");

        PlaylistEntity playlistEntity = PlayListMock.buildPlaylistEntity(1L, "name");

        when(playlistJpaRepository.countByName("name")).thenReturn(0);
        when(playlistJpaRepository.save(any(PlaylistEntity.class))).thenReturn(playlistEntity);

        // When
        Playlist savedPlaylist = playlistJpaAdapter.save(playlist);

        // Then
        assertNotNull(savedPlaylist);
        assertEquals(1L, savedPlaylist.getId());
        assertEquals("name", savedPlaylist.getName());
    }

    @Test
    void testSave_DuplicateRecordException() {
        // Given
        Playlist playlist = new Playlist();
        playlist.setName("Test Playlist");

        //when
        when(playlistJpaRepository.countByName("Test Playlist")).thenReturn(1);

        //Then
        assertThrows(DuplicateRecordException.class, () -> playlistJpaAdapter.save(playlist));
    }

    @Test
    void testFindAll() {
        // Given
        PlaylistEntity playlistEntity1 = PlayListMock.buildPlaylistEntity(1L, "name");
        PlaylistEntity playlistEntity2 = PlayListMock.buildPlaylistEntity(2L, "name2");
        List<PlaylistEntity> playlistEntities = Arrays.asList(playlistEntity1, playlistEntity2);

        // When
        when(playlistJpaRepository.findAll()).thenReturn(playlistEntities);

        // Then
        List<Playlist> playlists = playlistJpaAdapter.findAll();

        assertNotNull(playlists);
        assertEquals(2, playlists.size());
        assertEquals(1L, playlists.get(0).getId());
        assertEquals("name", playlists.get(0).getName());
        assertEquals(2L, playlists.get(1).getId());
        assertEquals("name2", playlists.get(1).getName());
    }

    @Test
    void testFindByName() {
        // Given
        String playlistName = "name";
        String description = "Description of Test Playlist";

        // When
        when(playlistJpaRepository.findDescriptionByName(playlistName)).thenReturn(Optional.of(description));

        // Then
        String foundDescription = playlistJpaAdapter.findByName(playlistName);

        assertNotNull(foundDescription);
        assertEquals(description, foundDescription);
    }

    @Test
    void testFindByName_PlayListNotFoundException() {
        // Given
        String playlistName = "NonexistentPlaylist";

        //When
        when(playlistJpaRepository.findDescriptionByName(playlistName)).thenReturn(Optional.empty());

        //Then
        assertThrows(PlayListNotFoundException.class, () -> playlistJpaAdapter.findByName(playlistName));
    }

    @Test
    void testDelete() {
        // Given
        String playlistName = "TestPlaylist";
        PlaylistEntity playlistEntity = PlayListMock.buildPlaylistEntity(1L, "TestPlaylist");

        // When
        when(playlistJpaRepository.findByName(playlistName)).thenReturn(Optional.of(playlistEntity));

        // Then
        playlistJpaAdapter.delete(playlistName);

        //assert
        verify(playlistJpaRepository, times(1)).delete(playlistEntity);
    }

    @Test
    void testDelete_PlayListNotFoundException() {
        // Given
        String playlistName = "NonexistentPlaylist";

        //When
        when(playlistJpaRepository.findByName(playlistName)).thenReturn(Optional.empty());

        //Tqhen
        assertThrows(PlayListNotFoundException.class, () -> playlistJpaAdapter.delete(playlistName));
    }
}