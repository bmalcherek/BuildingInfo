package com.io.buildings.controllers.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class BuildingRequest {

        @NotNull
        private String name;

        @NotNull
        private List<Integer> floorIds;
}
