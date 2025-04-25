package com.dw.NAMANSOLOJAVA.DTO;

import com.dw.NAMANSOLOJAVA.enums.AlarmType;
import com.dw.NAMANSOLOJAVA.model.Alarm;
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
    private String username;   // 알림을 보낸 사람 (기존 방식 유지할 경우 필요)
    private String recipient;  // 🔥 알림을 받은 사람 (프론트 필터링 기준)
    private AlarmType type;
    private String message;
    private LocalDateTime addDate;
    private boolean isRead;
    private String icon;

    public AlarmDTO(Alarm alarm) {
        this.id = alarm.getId();
        this.username = extractSender(alarm.getMessage());   // 메시지에서 보낸 사람 추정 or 분리 로직
        this.recipient = alarm.getUser().getUsername();      // 🔥 핵심: 알람 수신자 명시
        this.type = alarm.getAlarmType();
        this.message = alarm.getMessage();
        this.addDate = alarm.getAddDate();
        this.isRead = alarm.isRead();
        this.icon = alarm.getIcon();
    }

    private String extractSender(String message) {
        try {
            return message.substring(0, message.indexOf("님"));
        } catch (Exception e) {
            return "unknown";
        }
    }
}