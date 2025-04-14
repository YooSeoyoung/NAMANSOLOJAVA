package com.dw.NAMANSOLOJAVA.chat;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

    private MessageType type;
    private AlarmMessage alarmMessage;
    private String content; // 메시지 글
}