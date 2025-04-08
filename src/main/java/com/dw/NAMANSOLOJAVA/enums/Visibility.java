package com.dw.NAMANSOLOJAVA.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Visibility {
    PUBLIC("누구나"),       // 전체 공개
    PRIVATE("우리만"),      // 나만 보기
    FOLLOWERS_ONLY("팔로워들과"); // 팔로워만 보기 (추후 구현 대비)

    private final String visible;
}
