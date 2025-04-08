package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AlarmDTO {
    private Long id;
    private String username;
    private String alarmType;
    private String message;
    private LocalDateTime addDate;
    private boolean isRead;
    private String weatherInfo;
}
