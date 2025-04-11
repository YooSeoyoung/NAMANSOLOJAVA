package com.dw.NAMANSOLOJAVA.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AlarmType {
    FOLLOW, // 유저가 다른 유저를 팔로우했을때
    ALBUM, // 팔로우한 유저가 게시물을 올렸을때
    COMMENT, // 내가 올린 게시물에 댓글이 달렸을때
    GREAT, // 내 게시물에 좋아요가 눌렸을때
    EVENT, // 관리자가 이벤트 상품을 등록했을때
    RECOMMEND, // 장소를 추천받을때 받는 알람
    RECOMMENT, // 내가 단 댓글에 대댓글이 달렸을때
    TODO, // 기념일이나 일정이 있을떄
    WEATHER // 날씨 정보를 알려줄때
}

