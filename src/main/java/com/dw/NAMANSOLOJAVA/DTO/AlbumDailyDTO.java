package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class AlbumDailyDTO {
    private LocalDate date;
    private Long count;

    // 쿼리가 DATE() 쓸 때 필요한 생성자
    public AlbumDailyDTO(Date sqlDate, Long count) {
        this.date = sqlDate.toLocalDate();
        this.count = count;
    }

    // 기존 LocalDateTime 용 생성자도 유지
    public AlbumDailyDTO(LocalDateTime datetime, Long count) {
        this.date = datetime.toLocalDate();
        this.count = count;
    }
}
