package com.suffragium.main.service;

import com.suffragium.main.model.Room;
import com.suffragium.main.model.SongSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SongService {

    @Autowired
    private RoomService roomService;

    public void suggestSong(String roomId, String songUri, String userId) {
        Room room = roomService.getRoom(roomId);
        if (room != null) {
            room.addSuggestion(new SongSuggestion(songUri, userId));
        }
    }

    public void voteSong(String roomId, String songUri, String userId) {
        Room room = roomService.getRoom(roomId);
        if (room != null) {
            room.voteSong(songUri, userId);
        }
    }

    public List<SongSuggestion> getSuggestedSongs(String roomId) {
        Room room = roomService.getRoom(roomId);
        return room != null ? room.getSuggestions() : Collections.emptyList();
    }
}
