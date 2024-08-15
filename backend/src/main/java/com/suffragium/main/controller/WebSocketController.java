package com.suffragium.main.controller;

import com.suffragium.main.model.SongUpdateMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @MessageMapping("/room/{roomId}/song")
    @SendTo("/topic/room/{roomId}")
    public SongUpdateMessage songUpdate(@DestinationVariable String roomId, SongUpdateMessage message) {
        // Process the song update (e.g., new suggestion, vote, or currently playing song)
        return message;
    }
}
