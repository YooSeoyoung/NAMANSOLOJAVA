package com.dw.NAMANSOLOJAVA.Config;

import com.dw.NAMANSOLOJAVA.chat.ChatMessage;
import com.dw.NAMANSOLOJAVA.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessagingTemplate messagingTemplate;

//    @EventListener
//    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
//        //누군가가 브라우저가 닫으면 websocket 연결이 끊겼으면 다른 클라이언트에게 'ㅇㅇㅇ이 나갔습니다'라고 자동으로 처리
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//        String username = (String) headerAccessor.getSessionAttributes().get("username");
//        if (username != null) {
//            log.info("user disconnected: {}", username);
//            var chatMessage = ChatMessage.builder()
//                    .type(MessageType.NOTIFICATION)
////                    .sender(username)
//                    .build();
//            messagingTemplate.convertAndSend("/topic/public", chatMessage);
//        }
//    }

}