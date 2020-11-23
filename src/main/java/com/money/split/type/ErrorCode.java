package com.money.split.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Common
    NOT_DEFINED("A001", "정의 되지 않은 에러입니다."),
    NULL_POINT("A002", "null point 에러 입니다."),

    // Split
    NOT_VALID_TOKEN("R001", "유효한 토큰이 아닙니다."),
    OTHER_ROOM_SPLIT("R002", "다른 방의 뿌리기입니다."),
    DONT_SELF_SPLIT("R003", "본인의 뿌리기는 받을 수 없습니다."),
    DONT_TWICE_RECEIVE("R004", "두 번 받을 수 없습니다."),
    ALL_SPLIT("R005", "다 분배되어 받을 수 없습니다."),
    SPLIT_TIME_OVER("R006", "뿌린지 10분이 지나 받을 수 없습니다."),
    NOT_SELF_TOKEN("R007", "본인의 토큰이 아닙니다."),
    SPLIT_7DAY_OVER("R008", "뿌린지 7일 지나 조회 할 수 없습니다."),
    ;

    private final String code;
    private final String message;
}
