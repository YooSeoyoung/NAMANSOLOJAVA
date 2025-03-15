package com.dw.TheBoxer.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Reaction {

    LIKE("좋아요"),
    DISLIKE("싫어요");

    private final String reaction;
}
