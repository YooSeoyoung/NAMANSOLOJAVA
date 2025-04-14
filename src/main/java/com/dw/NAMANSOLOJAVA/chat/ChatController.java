package com.dw.NAMANSOLOJAVA.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(
            @Payload ChatMessage chatMessage
    ) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser") // join할때 쓰이는것
    @SendTo("/topic/public") // 입장을 알려주는것
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        // 연결종료시 누구의 연결이 종료된 것인지 확인하기 위해 저장
        headerAccessor.getSessionAttributes().put("username", chatMessage.getContent());
        return chatMessage;
    }
}
