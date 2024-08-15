package com.suffragium.main.config;

import com.suffragium.main.model.Room;
import com.suffragium.main.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulerConfig {
    @Autowired
    private RoomService roomService;

    @Scheduled(fixedRate = 5000) // Check every 5 seconds
    public void checkAndSelectNextSong() {
        for (Room room : roomService.getAllRooms()) {
            roomService.selectNextSong(room.getRoomId());
        }
    }
}
