package com.io.buildings.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class Localization {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    Localization(String name) {
        this.name = name;
    }

    public abstract Float countSurface();
}
