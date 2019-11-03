package com.io.buildings.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Room extends Localization {

    @Column(nullable = false)
    private Float area;

    @Column(nullable = false)
    private Float cube;

    @Column(nullable = false)
    private Float heating;

    @Column(nullable = false)
    private Float light;
}
