package com.money.split.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Room extends BaseEntity {
    @Column(name = "room")
    private String room;

    @Column(name = "user")
    private Integer user;
}
