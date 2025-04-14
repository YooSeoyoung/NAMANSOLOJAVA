package com.dw.NAMANSOLOJAVA.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MediaType {
    PICTURE,
    VIDEO;

    public static MediaType fromMimeType(String contentType) {
        if (contentType.startsWith("image/")) {
            return PICTURE;
        }
        else if (contentType.startsWith("video/")) {
            return VIDEO;
        }
        else {
            throw new IllegalArgumentException("지원하지 않는 타입입니다: " + contentType);
        }
    }
}
