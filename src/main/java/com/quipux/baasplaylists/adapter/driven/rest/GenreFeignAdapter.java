package com.quipux.baasplaylists.adapter.driven.rest;

import com.quipux.baasplaylists.adapter.driven.rest.model.GenresRs;
import com.quipux.baasplaylists.adapter.driven.rest.model.GetTokenFormDataRq;
import com.quipux.baasplaylists.adapter.driven.rest.model.GetTokenRs;
import com.quipux.baasplaylists.domain.model.Genres;
import com.quipux.baasplaylists.domain.repository.GenreRepositoryPort;
import com.quipux.baasplaylists.utils.constant.Constant;
import com.quipux.baasplaylists.utils.exception.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class GenreFeignAdapter implements GenreRepositoryPort {

    private final GenreFeignClient genreFeignClient;
    private final TokenFeignClient tokenFeignClient;

    public GenreFeignAdapter(GenreFeignClient genreFeignClient, TokenFeignClient tokenFeignClient) {
        this.genreFeignClient = genreFeignClient;
        this.tokenFeignClient = tokenFeignClient;
    }

    @Override
    public Genres findAllGenres() {
        ResponseEntity<GenresRs> genresRsEntity;
        Map<String, Object> headers = new HashMap<>();
        headers.put("Authorization", buildToken());
        try {
            genresRsEntity =  genreFeignClient.getGenres(headers);
        } catch (Exception ex) {
            throw new BadRequestException("Error trying to consume spotify services");
        }
        return Genres.builder()
                .genres(Objects.requireNonNull(genresRsEntity.getBody()).getGenres())
                .build();
    }

    private String buildToken() {
        GetTokenRs token = getSpotifyToken();
        return String.format("%s %s", token.getTokenType(), token.getAccessToken());
    }

    private GetTokenRs getSpotifyToken() {
        return tokenFeignClient.getToken(buildGetTokenFormDataRq());
    }

    private GetTokenFormDataRq buildGetTokenFormDataRq() {
        return GetTokenFormDataRq.builder()
                .grant_type(Constant.GRANT_TYPE)
                .client_id(Constant.CLIENT_ID)
                .client_secret(Constant.CLIENT_SECRET)
                .build();
    }
}
