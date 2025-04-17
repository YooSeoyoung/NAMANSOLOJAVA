package com.dw.NAMANSOLOJAVA.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlarmSettingDTO {
    private boolean follow;
    private boolean comment;
    private boolean great;
    private boolean recomment;
    private boolean todo;
    private boolean weather;
}
