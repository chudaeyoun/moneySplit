package com.money.split.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {
    OK("success"),
    FAIL("error");

    private final String message;
}
