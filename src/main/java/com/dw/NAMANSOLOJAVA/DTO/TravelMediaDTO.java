package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Setter
public class TravelMediaDTO {
    private String name;
    private List<MultipartFile> multipartFiles;
}
