package com.example.desafio.dto;


import com.google.gson.GsonBuilder;
import com.mapbox.services.commons.geojson.custom.PositionDeserializer;
import com.mapbox.services.commons.geojson.custom.PositionSerializer;
import com.mapbox.services.commons.models.Position;

public class MultiPolygonDTO {

    private String type;

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

    public static MultiPolygonDTO fromJson(String json) {
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(Position.class, new PositionDeserializer());
        return (MultiPolygonDTO) gson.create().fromJson(json, MultiPolygonDTO.class);
    }

    public String toJson() {
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(Position.class, new PositionSerializer());
        return gson.create().toJson(this);
    }

}
