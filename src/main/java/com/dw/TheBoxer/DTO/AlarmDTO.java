package com.dw.TheBoxer.DTO;

import com.dw.TheBoxer.enums.AlarmType;
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
    private AlarmType alarmType;
    private String message;
    private LocalDateTime addDate;
    private boolean isRead;
    private String weatherInfo;
}
