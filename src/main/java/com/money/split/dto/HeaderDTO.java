package com.money.split.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class HeaderDTO {
    private Integer userId;
    private String roomId;
}
