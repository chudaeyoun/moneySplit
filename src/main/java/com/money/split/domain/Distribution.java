package com.money.split.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "distributions")
@Builder
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Distribution extends BaseEntity {
    @Column(name = "token", length = 3)
    private String token;

    @Column(name = "money")
    private Long money;

    @Column(name = "user")
    private Integer user;

    @Column(name = "room")
    private String room;

    @Column(name = "use")
    private String use;

    @Column(name = "create_user")
    private Integer createUser;
}
