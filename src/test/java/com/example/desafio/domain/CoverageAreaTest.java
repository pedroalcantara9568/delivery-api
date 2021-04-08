package com.example.desafio.domain;

import com.mapbox.geojson.MultiPolygon;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public class CoverageAreaTest {

    CoverageArea coverageArea;

    MultiPolygon multiPolygon;

    @BeforeEach
    public void setup() {
        List<List<Point>> coordenadas = Collections.singletonList(asList(
                Point.fromLngLat(30, 20),
                Point.fromLngLat(45, 40),
                Point.fromLngLat(10, 40),
                Point.fromLngLat(30, 20),
                Point.fromLngLat(15, 5),
                Point.fromLngLat(40, 10),
                Point.fromLngLat(10, 20),
                Point.fromLngLat(5, 10),
                Point.fromLngLat(15, 5)));
        Polygon polygon = Polygon.fromLngLats(coordenadas);

        multiPolygon = MultiPolygon.fromPolygon(polygon);
        coverageArea = CoverageArea.fromJson(multiPolygon.toJson());

    }

    @Test
    public void deveEstaContido() {
        double[] coordenadas = {30, 30};
        Addres addres = new Addres("Point", coordenadas);
        boolean estaContido = this.coverageArea.estaContido(addres);

        Assertions.assertTrue(estaContido);
    }

    @Test
    public void naoDeveEstaContido() {
        double[] coordenadas = {-30, -20};
        Addres addres = new Addres("Point", coordenadas);
        boolean naoEstaContido = this.coverageArea.estaContido(addres);

        Assertions.assertFalse(naoEstaContido);
    }

    @Test
    public void distanciaDeveSer() {
        Point ponto = Point.fromLngLat(30, 30);
        Point pontoReferencia = Point.fromLngLat(31, 31);

        double distanciaEmCentimetros = this.coverageArea.distanciaEmCentimetros(ponto, pontoReferencia);

        Assertions.assertNotNull(distanciaEmCentimetros);
    }

}
