package com.example.desafio.domain;

import com.google.gson.GsonBuilder;
import com.mapbox.geojson.MultiPolygon;
import com.mapbox.geojson.Point;
import com.mapbox.services.commons.geojson.custom.PositionDeserializer;
import com.mapbox.services.commons.geojson.custom.PositionSerializer;
import com.mapbox.services.commons.models.Position;
import com.mapbox.turf.TurfConstants;
import com.mapbox.turf.TurfJoins;
import com.mapbox.turf.TurfMeasurement;

import java.io.Serializable;

public class CoverageArea implements Serializable {

    private String type;

    private double[][][][] coordinates;

    public CoverageArea(String type, double[][][][] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double[][][][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[][][][] coordinates) {
        this.coordinates = coordinates;
    }

    public static CoverageArea fromJson(String json) {
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(Position.class, new PositionDeserializer());
        return gson.create().fromJson(json, CoverageArea.class);
    }

    public String toJson() {
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(Position.class, new PositionSerializer());
        return gson.create().toJson(this);
    }

    public boolean estaContido(Addres addres) {
        Point point = Point.fromJson(addres.toJson());
        MultiPolygon multiPolygon = MultiPolygon.fromJson(this.toJson());
        return TurfJoins.inside(point, multiPolygon);
    }

    public double distanciaEmCentimetros(Point point, Point pontoReferencia) {
        return TurfMeasurement.distance(pontoReferencia, point, TurfConstants.UNIT_CENTIMETERS);
    }

}
