package com.quipux.baasplaylists.adapter.driven.rest;

import com.quipux.baasplaylists.adapter.driven.rest.model.GenresRs;
import com.quipux.baasplaylists.utils.constant.Constant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(url = "${services.spotify.api.url}", name = "spotify")
public interface GenreFeignClient {

    @GetMapping(value = Constant.GET_SPOTIFY_GENRES)
    ResponseEntity<GenresRs> getGenres(@RequestHeader Map<String, Object> headers);
}
