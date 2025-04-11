package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ToDoAllDTO {
    List<ToDoTravelDTO> toDoTravelDTOs;
    List<AnniversaryDTO> anniversaryDTOs;
}
