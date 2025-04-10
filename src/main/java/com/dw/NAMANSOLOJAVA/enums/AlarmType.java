package com.dw.NAMANSOLOJAVA.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AlarmType {
    FOLLOW, // 유저가 다른 유저를 팔로우했을때 받는 타입
    ALBUM,
    COMMENT,
    GREAT,
    EVENT,
    RECOMMEND,
    RECOMMENT,
    TODO,
    WEATHER
}

