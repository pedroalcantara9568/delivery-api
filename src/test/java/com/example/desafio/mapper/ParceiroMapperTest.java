package com.example.desafio.mapper;

import com.example.desafio.domain.Addres;
import com.example.desafio.domain.CoverageArea;
import com.example.desafio.domain.Parceiro;
import com.example.desafio.dto.AddressDTO;
import com.example.desafio.dto.CoverageAreaDTO;
import com.example.desafio.dto.ParceiroDTO;
import com.example.desafio.dto.mapper.ParceiroMapper;
import com.mapbox.geojson.MultiPolygon;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

@SpringBootTest
public class ParceiroMapperTest {

    Parceiro parceiro;

    ParceiroDTO parceiroDTO;

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
        Addres endereco = new Addres("Point", arapiraca);

        parceiro = Parceiro.builder()
                .id("1")
                .document("10323323232")
                .tradingName("Boteco do Morxes")
                .ownerName("Anderson Ribeiro")
                .addres(endereco)
                .coverageArea(coverageArea)
                .build();

        parceiroDTO = ParceiroDTO.builder()
                .document("123456789")
                .tradingName("Boteco do Cabeça")
                .ownerName("Pedro Alcântara")
                .coverageArea(CoverageAreaDTO.fromJson(multiPolygon.toJson()))
                .address(AddressDTO.fromJson(endereco.toJson()))
                .build();

    }

    @Test
    @DisplayName("Converte entidade Pessoa em PessoaDTO")
    public void domainToDTO() {
        ParceiroDTO parceiroDTO = ParceiroMapper.toDTO(parceiro);

        Assertions.assertEquals(parceiro.getTradingName(), parceiroDTO.getTradingName());
        Assertions.assertEquals(parceiro.getDocument(), parceiroDTO.getDocument());
        Assertions.assertEquals(parceiro.getOwnerName(), parceiroDTO.getOwnerName());
        Assertions.assertEquals(parceiro.getAddres().getCoordinates(), parceiroDTO.getAddress().getCoordinates());
        Assertions.assertEquals(parceiro.getCoverageArea().getCoordinates(), parceiroDTO.getCoverageArea().getCoordinates());
    }

    @Test
    @DisplayName("Converte entidade Pessoa em PessoaDTO")
    public void dtoToDomain() {
        Parceiro parceiroConvertido = ParceiroMapper.toEntity(parceiroDTO);

        Assertions.assertEquals(parceiroConvertido.getTradingName(), parceiroDTO.getTradingName());
        Assertions.assertEquals(parceiroConvertido.getDocument(), parceiroDTO.getDocument());
        Assertions.assertEquals(parceiroConvertido.getOwnerName(), parceiroDTO.getOwnerName());
        Assertions.assertEquals(parceiroConvertido.getAddres().getCoordinates(), parceiroDTO.getAddress().getCoordinates());
        Assertions.assertEquals(parceiroConvertido.getCoverageArea().getCoordinates(), parceiroDTO.getCoverageArea().getCoordinates());

    }
}
