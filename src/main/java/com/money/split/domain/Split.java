package com.money.split.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "splits")
@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Split extends BaseEntity {
    @Column(name = "token", length = 3)
    private String token;

    @Column(name = "user")
    private Integer user;

    @Column(name = "room")
    private String room;

    @Column(name = "money")
    private Long money;

    @Column(name = "count")
    private Integer count;
}
