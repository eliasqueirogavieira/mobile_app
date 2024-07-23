package com.suffragium.main.aspect;


import com.suffragium.main.model.Room;
import com.suffragium.main.service.RoomService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RoomOwnerAspect {

    @Autowired
    private RoomService roomService;

    @Around("@annotation(com.example.myapp.annotation.RoomOwnerOnly) && args(roomId,..)")
    public Object checkRoomOwner(ProceedingJoinPoint joinPoint, String roomId) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2User principal = (OAuth2User) authentication.getPrincipal();
            Room room = roomService.getRoom(roomId);
            if (room != null && room.getOwnerId().equals(principal.getName())) {
                return joinPoint.proceed();
            }
        }
        throw new AccessDeniedException("Only room owner can perform this action");
    }
}
