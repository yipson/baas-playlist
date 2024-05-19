package com.quipux.baasplaylists.adapter.driven.rest.config;

import feign.form.FormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormEncoderConfig {
    @Bean
    public feign.codec.Encoder feignFormEncoder() {
        return new FormEncoder();
    }
}
