package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UpdateImageDDayDTO {
    PictureAndVideoDTO mediaUrl;
    LocalDate dDay;
}
