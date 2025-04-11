package com.dw.NAMANSOLOJAVA.chat;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

    private MessageType type;
    private String content; // 메시지 글
    private String sender; // 채팅방
    private String receiver; // 프라이빗 개인톡용
}