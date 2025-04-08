package com.dw.TheBoxer.DTO;

import com.dw.TheBoxer.enums.MediaType;
import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PictureAndVideoDTO {
    private String mediaUrl;
    private String mediaType;
}
