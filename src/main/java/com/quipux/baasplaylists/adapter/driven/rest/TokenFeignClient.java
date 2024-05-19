package com.quipux.baasplaylists.adapter.driven.rest;

import com.quipux.baasplaylists.adapter.driven.rest.config.FormEncoderConfig;
import com.quipux.baasplaylists.adapter.driven.rest.model.GetTokenFormDataRq;
import com.quipux.baasplaylists.adapter.driven.rest.model.GetTokenRs;
import com.quipux.baasplaylists.utils.constant.Constant;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "spotify-token", url = "${services.spotify.account.url}", configuration = FormEncoderConfig.class)
public interface TokenFeignClient {

    @RequestMapping(value = Constant.GET_SPOTIFY_TOKEN,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Headers(Constant.FORM_URLENCODED_HEADER)
    GetTokenRs getToken(GetTokenFormDataRq formData);
}
