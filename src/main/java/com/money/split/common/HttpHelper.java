package com.money.split.common;

import com.money.split.dto.HeaderDTO;
import com.money.split.type.HeaderNames;

import javax.servlet.http.HttpServletRequest;

public class HttpHelper {
    static public HeaderDTO extraHeader(HttpServletRequest request) {
        return HeaderDTO.builder()
                .userId(Integer.parseInt(request.getHeader(HeaderNames.USER.getName())))
                .roomId(request.getHeader(HeaderNames.ROOM.getName()))
                .build();
    }
}
