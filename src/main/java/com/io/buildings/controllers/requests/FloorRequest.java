package com.io.buildings.controllers.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class FloorRequest {

    @NotNull
    private String name;

    @NotNull
    private List<Integer> roomIds;
}
