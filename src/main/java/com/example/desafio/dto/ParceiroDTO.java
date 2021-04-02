package com.example.desafio.dto;

import com.mapbox.geojson.MultiPolygon;
import com.mapbox.geojson.Point;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Builder
public class ParceiroDTO {

    @NotBlank
    private String tradingName;

    @NotBlank
    private String ownerName;

    @NotBlank
    private String document;

    public MultiPolygon coverageArea;

    public Point address;

}
