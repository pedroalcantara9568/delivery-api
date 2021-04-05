package com.example.desafio.dto;

import com.google.gson.GsonBuilder;
import com.mapbox.services.commons.geojson.custom.PositionDeserializer;
import com.mapbox.services.commons.geojson.custom.PositionSerializer;
import com.mapbox.services.commons.models.Position;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AddressDTO {

    @NotNull
    @Pattern(regexp = "Point")
    private String type;

    @NotNull
    @Size(min = 2, max = 2)
    private double[] coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public static AddressDTO fromJson(String json) {
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(Position.class, new PositionDeserializer());
        return gson.create().fromJson(json, AddressDTO.class);
    }

    public String toJson() {
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(Position.class, new PositionSerializer());
        return gson.create().toJson(this);
    }

}
