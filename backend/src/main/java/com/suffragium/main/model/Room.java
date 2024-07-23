package com.suffragium.main.model;

import java.util.*;
import java.util.stream.Collectors;

public class Room {
    private String roomId;
    private String ownerId;
    private String playlistId;
    private List<SongSuggestion> suggestions;
    private Map<String, Set<String>> votes; // songUri -> Set of userIds who voted

    public Room(String roomId, String ownerId) {
        this.roomId = roomId;
        this.ownerId = ownerId;
        this.suggestions = new ArrayList<>();
        this.votes = new HashMap<>();
    }

    // Getters and setters

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public List<SongSuggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<SongSuggestion> suggestions) {
        this.suggestions = suggestions;
    }

    public Map<String, Set<String>> getVotes() {
        return votes;
    }

    public void setVotes(Map<String, Set<String>> votes) {
        this.votes = votes;
    }

    public void addSuggestion(SongSuggestion suggestion) {
        this.suggestions.add(suggestion);
    }

    public void voteSong(String songUri, String userId) {
        for (SongSuggestion suggestion : suggestions) {
            if (suggestion.getSongUri().equals(songUri)) {
                suggestion.addVote(userId);
                break;
            }
        }
    }

    public List<SongSuggestion> getSortedSuggestions() {
        return suggestions.stream()
                .sorted(Comparator.comparingInt((SongSuggestion s) -> s.getVotes().size()).reversed())
                .collect(Collectors.toList());
    }
}
