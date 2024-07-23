package com.suffragium.main.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class SpotifyService {
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    public void playSong(String userId, String songUri) {
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient("spotify", userId);
        if (authorizedClient != null) {
            webClientBuilder.build()
                    .put()
                    .uri("https://api.spotify.com/v1/me/player/play")
                    .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
                    .bodyValue("{\"uris\":[\"" + songUri + "\"]}")
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        }
    }

    // Add other Spotify API methods here
}
