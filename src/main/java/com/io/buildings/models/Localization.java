package com.io.buildings.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class Localization {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;
}
