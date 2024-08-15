package com.suffragium.main.model;

import java.util.HashSet;
import java.util.Set;

public class SongSuggestion {
    private String songUri;
    private String userId;
    private Set<String> votes;

    public SongSuggestion(String songUri, String userId) {
        this.songUri = songUri;
        this.userId = userId;
        this.votes = new HashSet<>();
    }

    // Getters and setters

    public String getSongUri() {
        return songUri;
    }

    public void setSongUri(String songUri) {
        this.songUri = songUri;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<String> getVotes() {
        return votes;
    }

    public void addVote(String userId) {
        votes.add(userId);
    }
}
