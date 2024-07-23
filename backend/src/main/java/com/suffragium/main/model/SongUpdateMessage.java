package com.suffragium.main.model;

public class SongUpdateMessage {
    private String type;
    private String songUri;

    public SongUpdateMessage(String type, String songUri) {
        this.type = type;
        this.songUri = songUri;
    }

    // Getters and setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSongUri() {
        return songUri;
    }

    public void setSongUri(String songUri) {
        this.songUri = songUri;
    }
}

