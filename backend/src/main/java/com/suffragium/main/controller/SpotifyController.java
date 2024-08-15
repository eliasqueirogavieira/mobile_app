package com.suffragium.main.controller;

import com.suffragium.main.model.AddTrackRequest;
import com.suffragium.main.model.CreatePlaylistRequest;
import com.suffragium.main.model.PlayPlaylistRequest;
import com.suffragium.main.model.PlaySongRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/api/spotify")
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

    @PutMapping("/play")
    public ResponseEntity<String> playSong(@RequestBody PlaySongRequest request, @RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient authorizedClient) {
        String response = webClientBuilder.build()
                .put()
                .uri("https://api.spotify.com/v1/me/player/play")
                .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
                .bodyValue("{\"uris\":[\"" + request.getSongUri() + "\"]}")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/play-playlist")
    public ResponseEntity<String> playPlaylist(@RequestBody PlayPlaylistRequest request, @RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient authorizedClient) {
        String response = webClientBuilder.build()
                .put()
                .uri("https://api.spotify.com/v1/me/player/play")
                .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
                .bodyValue("{\"context_uri\":\"" + request.getPlaylistUri() + "\"}")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/playlists/{playlistId}/tracks")
    public ResponseEntity<String> addTrackToPlaylist(@PathVariable String playlistId, @RequestBody AddTrackRequest request, @RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient authorizedClient) {
        String response = webClientBuilder.build()
                .post()
                .uri("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks")
                .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
                .bodyValue("{\"uris\":[\"" + request.getTrackUri() + "\"]}")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchTracks(@RequestParam String query, @RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient authorizedClient) {
        String response = webClientBuilder.build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("https://api.spotify.com/v1/search")
                        .queryParam("q", query)
                        .queryParam("type", "track")
                        .build())
                .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/playlists")
    public ResponseEntity<String> getUserPlaylists(@RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient authorizedClient) {
        String response = webClientBuilder.build()
                .get()
                .uri("https://api.spotify.com/v1/me/playlists")
                .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/currently-playing")
    public ResponseEntity<String> getCurrentlyPlaying(@RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient authorizedClient) {
        String response = webClientBuilder.build()
                .get()
                .uri("https://api.spotify.com/v1/me/player/currently-playing")
                .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/next")
    public ResponseEntity<Void> skipToNext(@RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient authorizedClient) {
        webClientBuilder.build()
                .post()
                .uri("https://api.spotify.com/v1/me/player/next")
                .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
                .retrieve()
                .toBodilessEntity()
                .block();

        return ResponseEntity.ok().build();
    }

    @PostMapping("/previous")
    public ResponseEntity<Void> skipToPrevious(@RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient authorizedClient) {
        webClientBuilder.build()
                .post()
                .uri("https://api.spotify.com/v1/me/player/previous")
                .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
                .retrieve()
                .toBodilessEntity()
                .block();

        return ResponseEntity.ok().build();
    }

    @PutMapping("/pause")
    public ResponseEntity<Void> pausePlayback(@RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient authorizedClient) {
        webClientBuilder.build()
                .put()
                .uri("https://api.spotify.com/v1/me/player/pause")
                .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
                .retrieve()
                .toBodilessEntity()
                .block();

        return ResponseEntity.ok().build();
    }

    @PutMapping("/resume")
    public ResponseEntity<Void> resumePlayback(@RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient authorizedClient) {
        webClientBuilder.build()
                .put()
                .uri("https://api.spotify.com/v1/me/player/play")
                .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
                .retrieve()
                .toBodilessEntity()
                .block();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/tracks/{trackId}")
    public ResponseEntity<String> getTrackInfo(@PathVariable String trackId, @RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient authorizedClient) {
        String response = webClientBuilder.build()
                .get()
                .uri("https://api.spotify.com/v1/tracks/" + trackId)
                .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/playlists")
    public ResponseEntity<String> createPlaylist(@RequestBody CreatePlaylistRequest request, @RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient authorizedClient) {
        String response = webClientBuilder.build()
                .post()
                .uri("https://api.spotify.com/v1/me/playlists")
                .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/recommendations")
    public ResponseEntity<String> getRecommendations(@RequestParam List<String> seedTracks, @RegisteredOAuth2AuthorizedClient("spotify") OAuth2AuthorizedClient authorizedClient) {
        String response = webClientBuilder.build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("https://api.spotify.com/v1/recommendations")
                        .queryParam("seed_tracks", String.join(",", seedTracks))
                        .build())
                .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return ResponseEntity.ok(response);
    }
}
