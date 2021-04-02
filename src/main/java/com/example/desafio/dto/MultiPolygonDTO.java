package com.example.desafio.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.List;

public class MultiPolygonDTO {

    @NotBlank
    @Pattern(regexp = "MultiPolygon")
    private String type;

    @NotNull
    private double[][][][] coordinates;

    public MultiPolygonDTO(String type, double[][][][] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public double[][][][] getCoordinates() {
        return coordinates;
    }

}
