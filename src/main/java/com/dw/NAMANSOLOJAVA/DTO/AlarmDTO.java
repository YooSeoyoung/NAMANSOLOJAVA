package com.dw.NAMANSOLOJAVA.DTO;

import com.dw.NAMANSOLOJAVA.enums.AlarmType;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AlarmDTO {
    private Long id;
    private String username;
    private AlarmType type;
    private String message;
    private LocalDateTime addDate;
    private boolean isRead;
    private String icon;
}
