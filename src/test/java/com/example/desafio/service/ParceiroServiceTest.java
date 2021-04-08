package com.example.desafio.service;

import com.example.desafio.domain.Addres;
import com.example.desafio.domain.CoverageArea;
import com.example.desafio.domain.Parceiro;
import com.example.desafio.dto.ParceiroDTO;
import com.example.desafio.repository.ParceiroRepository;
import com.example.desafio.service.impl.ParceiroServiceImpl;
import com.mapbox.geojson.MultiPolygon;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class ParceiroServiceTest {

    ParceiroRepository parceiroRepository;

    ParceiroServiceImpl parceiroService;

    private Parceiro parceiroMaceio;

    private Parceiro parceiroArapiraca;

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

        MultiPolygon multiPolygon = MultiPolygon.fromPolygon(polygon);
        CoverageArea coverageArea = CoverageArea.fromJson(multiPolygon.toJson());


        double[] arapiraca = {25, 25};
        Addres endereco1 = new Addres("Point", arapiraca);
        parceiroArapiraca = Parceiro.builder()
                .id("1")
                .document("10323323232")
                .tradingName("Boteco do Morxes")
                .ownerName("Anderson Ribeiro")
                .addres(endereco1)
                .coverageArea(coverageArea)
                .build();


        double[] maceio = {30, 30};
        Addres endereco2 = new Addres("Point", maceio);
        parceiroMaceio = Parceiro.builder()
                .id("2")
                .document("32323423232")
                .tradingName("Boteco do Dale")
                .ownerName("Gabriel Martelo")
                .addres(endereco2)
                .coverageArea(coverageArea)
                .build();


        this.parceiroRepository = mock(ParceiroRepository.class);
        this.parceiroService = new ParceiroServiceImpl(parceiroRepository);

        Mockito.when(parceiroRepository.findById(any(String.class))).thenReturn(Optional.of(parceiroArapiraca));
        Mockito.when(parceiroRepository.findAll()).thenReturn(asList(parceiroArapiraca, parceiroMaceio));
        Mockito.when(parceiroRepository.save(parceiroArapiraca)).thenReturn(parceiroArapiraca);
    }

    @Test
    public void deveRetornarParceiroPorDocument() {
        ParceiroDTO parceiroDTO = this.parceiroService.cadastrar(parceiroArapiraca);

        assertEquals(parceiroArapiraca.getOwnerName(), parceiroDTO.getOwnerName());
        assertEquals(parceiroArapiraca.getDocument(), parceiroDTO.getDocument());
        assertEquals(parceiroArapiraca.getTradingName(), parceiroDTO.getTradingName());
    }

    @Test
    public void deveRetornarParceiroPorId() {
        ParceiroDTO parceiroDTO = this.parceiroService.buscarPorId("123456");

        assertEquals(parceiroArapiraca.getOwnerName(), parceiroDTO.getOwnerName());
        assertEquals(parceiroArapiraca.getDocument(), parceiroDTO.getDocument());
        assertEquals(parceiroArapiraca.getTradingName(), parceiroDTO.getTradingName());
    }

    @Test
    public void deveRetornarAlgumParceiro() {
        Optional<Parceiro> parceiro = this.parceiroService.buscarPorEndereco(27.5, 27.5);

        Assertions.assertTrue(parceiro.isPresent());
    }

    @Test
    public void naoDeveRetornarNenhumParceiro() {
        Optional<Parceiro> parceiro = this.parceiroService.buscarPorEndereco(-50.0, -50.0);

        Assertions.assertFalse(parceiro.isPresent());
    }

    @Test
    public void parceiroMaceioDeveserMaisProximo() {
        // maceio = {30, 30};
        Optional<Parceiro> parceiro = this.parceiroService.buscarPorEndereco(27.6, 27.6);

        assertEquals(parceiro.get(), parceiroMaceio);
    }

    @Test
    public void parceiroArapiracaDeveserMaisProximo() {
        // arapiraca = {25, 25};
        Optional<Parceiro> parceiro = this.parceiroService.buscarPorEndereco(27.4, 27.4);

        assertEquals(parceiro.get(), parceiroArapiraca);
    }


}
