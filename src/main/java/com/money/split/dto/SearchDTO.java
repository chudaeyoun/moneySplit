package com.money.split.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class SearchDTO {
    private String token;
    private LocalDateTime createAt;
    private Long throwingMoney;
    private Long receivedMoney;
    private Integer user;
    private String room;
    private List<ReceiveDTO> receiveDTOList;
}
