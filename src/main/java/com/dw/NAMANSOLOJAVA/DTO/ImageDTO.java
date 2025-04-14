package com.dw.NAMANSOLOJAVA.DTO;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ImageDTO {
    String title;
    MultipartFile image;
}
