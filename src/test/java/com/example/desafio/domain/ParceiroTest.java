package com.example.desafio.domain;

import com.mapbox.geojson.Point;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParceiroTest {

    private Parceiro parceiro;

    private CoverageArea coverageArea;

    @BeforeEach
    public void setup() {
        coverageArea = mock(CoverageArea.class);
        double[] coodenadas = {3, 3};
        parceiro = new Parceiro(new Addres("Point", coodenadas), coverageArea, null, null, null);
    }

    @Test
    public void deveCalcularAhDistanciaDoPonto() {
        double distanciaExperada = 3;
        Point pontoReferencia = Point.fromLngLat(3, 3);

        when(coverageArea.distanciaEmCentimetros(any(Point.class), any(Point.class))).thenReturn(distanciaExperada);
        double distanciaCalculada = this.parceiro.distanciaDe(pontoReferencia);
        Assertions.assertEquals(distanciaCalculada, distanciaExperada);
    }

    @Test
    public void parceiroDeveAtenderRegiao() {
        double[] coodenadas = {70, 70};
        when(coverageArea.estaContido(any(Addres.class))).thenReturn(true);
        Addres addres = new Addres("Point", coodenadas);

        boolean atendeRegiao = this.parceiro.atendeRegiao(addres);
        Assertions.assertTrue(atendeRegiao);
    }


}
