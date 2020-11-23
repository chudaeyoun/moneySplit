package com.money.split.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.money.split.domain.Split;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class SplitDTO {
    private String token;
    private Integer user;
    private String room;
    private Long money;
    private Integer count;

    public static Split convertToEntity(SplitDTO splitDTO) {
        return Split.builder()
                .token(splitDTO.getToken())
                .user(splitDTO.getUser())
                .room(splitDTO.getRoom())
                .money(splitDTO.getMoney())
                .count(splitDTO.getCount())
                .build();
    }
}
