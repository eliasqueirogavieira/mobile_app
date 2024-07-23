package com.suffragium.main.controller;

import com.suffragium.main.model.SongSuggestion;
import com.suffragium.main.model.SongSuggestionRequest;
import com.suffragium.main.model.VoteRequest;
import com.suffragium.main.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/api/rooms/{roomId}/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping("/suggest")
    public ResponseEntity<Void> suggestSong(@PathVariable String roomId, @RequestBody SongSuggestionRequest request, @AuthenticationPrincipal OAuth2User principal) {
        songService.suggestSong(roomId, request.getSongUri(), principal.getName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/vote")
    public ResponseEntity<Void> voteSong(@PathVariable String roomId, @RequestBody VoteRequest request, @AuthenticationPrincipal OAuth2User principal) {
        songService.voteSong(roomId, request.getSongUri(), principal.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<SongSuggestion>> getSuggestedSongs(@PathVariable String roomId) {
        return ResponseEntity.ok(songService.getSuggestedSongs(roomId));
    }
}
