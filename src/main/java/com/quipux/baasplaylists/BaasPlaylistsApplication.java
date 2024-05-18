package com.quipux.baasplaylists;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BaasPlaylistsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaasPlaylistsApplication.class, args);
	}

}
