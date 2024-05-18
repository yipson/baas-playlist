package com.quipux.baasplaylists.adapter.driving.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quipux.baasplaylists.adapter.driving.rest.model.CreatePlaylistDto;
import com.quipux.baasplaylists.adapter.driving.rest.model.DescriptionDto;
import com.quipux.baasplaylists.domain.model.Playlist;
import com.quipux.baasplaylists.domain.usecase.port.PlaylistPort;
import com.quipux.baasplaylists.mocks.PlayListMock;
import com.quipux.baasplaylists.utils.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlaylistControllerTest {

    @Mock
    private PlaylistPort playlistPort;

    @InjectMocks
    private PlaylistController playlistController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePlaylist() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        CreatePlaylistDto createPlaylistDto = PlayListMock.buildCreatePlaylistDto("name");
        Playlist playlist = PlayListMock.buildPlaylist("name");

        when(playlistPort.create(any(Playlist.class))).thenReturn(playlist);

        ResponseEntity<CreatePlaylistDto> response = playlistController.createPlaylist(createPlaylistDto);
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    void testCreatePlaylistWithInvalidName() {
        CreatePlaylistDto createPlaylistDto = new CreatePlaylistDto();
        createPlaylistDto.setName("");

        assertThrows(BadRequestException.class, () -> {
            playlistController.createPlaylist(createPlaylistDto);
        });
    }

    @Test
    void testGetPlayLists() {
        Playlist playlist1 = PlayListMock.buildPlaylist("Playlist1");
        Playlist playlist2 = PlayListMock.buildPlaylist("Playlist2");
        List<Playlist> playlists = Arrays.asList(playlist1, playlist2);
        CreatePlaylistDto dto1 = new CreatePlaylistDto();
        CreatePlaylistDto dto2 = new CreatePlaylistDto();

        when(playlistPort.getPlayLists()).thenReturn(playlists);

        ResponseEntity<List<CreatePlaylistDto>> response = playlistController.getPlayLists();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetDescription() {
        String listName = "Test Playlist";
        String description = "Test Description";

        when(playlistPort.getDescription(listName)).thenReturn(description);

        ResponseEntity<DescriptionDto> response = playlistController.getDescription(listName);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(description, response.getBody().getDescription());
    }

    @Test
    void testDeletePlayList() {
        String listName = "TestPlaylist";

        doNothing().when(playlistPort).delete(listName);

        ResponseEntity<Void> response = playlistController.deletePlayList(listName);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(playlistPort, times(1)).delete(listName);
    }
}