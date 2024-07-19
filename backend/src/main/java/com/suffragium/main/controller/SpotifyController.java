package com.suffragium.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api")
public class SpotifyController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/me")
    public ResponseEntity<String> getMe(@RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient authorizedClient) {
        String response = webClientBuilder.build()
                .get()
                .uri("https://api.spotify.com/v1/me")
                .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/play")
    public ResponseEntity<String> playPlaylist(@RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient authorizedClient) {
        String playlistUri = "spotify:playlist:7gCEHUvpoGZqr1p3Cn9sTU";

        String response = webClientBuilder.build()
                .put()
                .uri("https://api.spotify.com/v1/me/player/play")
                .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
                .bodyValue("{\"context_uri\":\"" + playlistUri + "\"}")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return ResponseEntity.ok(response);
    }
}
