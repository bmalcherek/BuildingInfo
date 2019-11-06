package com.io.buildings.controllers.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoomRequest {

    @NotNull
    private String name;

    @NotNull
    private Float area;

    @NotNull
    private Float cube;

    @NotNull
    private Float heating;

    @NotNull
    private Float light;
}
