package com.money.split.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HeaderNames {
    USER("X-USER-ID"),
    ROOM("X-ROOM-ID");

    private final String name;
}
