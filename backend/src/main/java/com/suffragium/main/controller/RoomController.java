package com.suffragium.main.controller;

import com.suffragium.main.annotation.RoomOwnerOnly;
import com.suffragium.main.model.Room;
import com.suffragium.main.model.UpdatePlaylistRequest;
import com.suffragium.main.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<Room> createRoom(@AuthenticationPrincipal OAuth2User principal) {
        String ownerId = principal.getName();
        Room room = roomService.createRoom(ownerId);
        return ResponseEntity.ok(room);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoom(@PathVariable String roomId) {
        Room room = roomService.getRoom(roomId);
        if (room == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(room);
    }

    @PutMapping("/{roomId}/playlist")
    @RoomOwnerOnly
    public ResponseEntity<Void> updateRoomPlaylist(@PathVariable String roomId, @RequestBody UpdatePlaylistRequest request) {
        // Update room playlist logic here
        return ResponseEntity.ok().build();
    }
}
