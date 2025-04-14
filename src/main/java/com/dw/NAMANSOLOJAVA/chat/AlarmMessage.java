package com.dw.NAMANSOLOJAVA.chat;

import com.dw.NAMANSOLOJAVA.enums.AlarmType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlarmMessage {

    private AlarmType type;
    private String content;
}

