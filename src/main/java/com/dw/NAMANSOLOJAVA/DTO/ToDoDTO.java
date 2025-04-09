package com.dw.NAMANSOLOJAVA.DTO;

import java.time.LocalDate;
import java.util.List;

public class ToDoDTO {
    private Long id;
    private String title; // 제목(첫 데이트, 놀이공원 놀러간 날)
    private LocalDate startDate; // 연애 시작일 등 기념일
    private LocalDate lastDate; // 여행 일정 끝나는 날
    private LocalDate finalEditDate; // 해당 일정에 대한 추가/수정 등 최종 수정 시간
    private String type;
    private Boolean editable;
    private List<MediaDTO> mediaUrls;
}
