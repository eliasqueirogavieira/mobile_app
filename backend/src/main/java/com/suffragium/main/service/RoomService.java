package com.suffragium.main.service;

import com.suffragium.main.model.Room;
import com.suffragium.main.model.SongSuggestion;
import com.suffragium.main.model.SongUpdateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RoomService {

    private Map<String, Room> rooms = new ConcurrentHashMap<>();

    @Autowired
    private SpotifyService spotifyService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public Room createRoom(String ownerId) {
        String roomId = generateUniqueId();
        Room room = new Room(roomId, ownerId);
        rooms.put(roomId, room);
        return room;
    }

    public Room getRoom(String roomId) {
        return rooms.get(roomId);
    }

    public Collection<Room> getAllRooms() {
        return rooms.values();
    }

    public void selectNextSong(String roomId) {
        Room room = getRoom(roomId);
        if (room != null && !room.getSortedSuggestions().isEmpty()) {
            SongSuggestion nextSong = room.getSortedSuggestions().get(0);
            spotifyService.playSong(room.getOwnerId(), nextSong.getSongUri());
            room.getSuggestions().remove(nextSong);
            room.getVotes().remove(nextSong.getSongUri());

            // Notify clients about the new playing song
            messagingTemplate.convertAndSend("/topic/room/" + roomId, new SongUpdateMessage("NOW_PLAYING", nextSong.getSongUri()));
        }
    }

    // Add methods for joining rooms, leaving rooms, etc.

    private String generateUniqueId() {
        // Implement unique ID generation logic
        return java.util.UUID.randomUUID().toString();
    }
}
