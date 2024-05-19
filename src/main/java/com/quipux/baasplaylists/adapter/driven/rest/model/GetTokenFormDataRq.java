package com.quipux.baasplaylists.adapter.driven.rest.model;

import feign.Param;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTokenFormDataRq {
    @Param("grant_type")
    private String grant_type;
    @Param("client_id")
    private String client_id;
    @Param("client_secret")
    private String client_secret;
}
