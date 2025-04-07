package com.dw.TheBoxer.DTO;

import com.dw.TheBoxer.model.Album;
import com.dw.TheBoxer.model.Tag;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AlbumTagDTO {

    private Long id;

    private Long albumId;

    private String tagName;
}
