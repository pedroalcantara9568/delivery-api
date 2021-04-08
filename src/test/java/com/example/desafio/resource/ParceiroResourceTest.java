package com.example.desafio.resource;

import com.example.desafio.domain.Addres;
import com.example.desafio.domain.CoverageArea;
import com.example.desafio.domain.Parceiro;
import com.example.desafio.dto.ParceiroDTO;
import com.example.desafio.repository.ParceiroRepository;
import com.example.desafio.service.ParceiroService;
import com.mapbox.geojson.MultiPolygon;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.desafio.dto.mapper.ParceiroMapper.toDTO;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static sun.security.timestamp.TSResponse.BAD_REQUEST;


@WebMvcTest
public class ParceiroResourceTest {

    @Autowired
    private ParceiroResource parceiroResource;

    @MockBean
    private ParceiroService parceiroService;

    @MockBean
    ParceiroRepository parceiroRepository;

    Parceiro parceiroArapiraca;

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
        Addres endereco1 = new Addres("Point", arapiraca);
        parceiroArapiraca = Parceiro.builder()
                .id("1")
                .document("10323323232")
                .tradingName("Boteco do Morxes")
                .ownerName("Anderson Ribeiro")
                .addres(endereco1)
                .coverageArea(coverageArea)
                .build();

        parceiroDTO = toDTO(parceiroArapiraca);

        standaloneSetup(this.parceiroResource);
    }

    @Test
    public void deveRetornarParceiroPorId() {
        when(this.parceiroService.buscarPorId(any(String.class)))
                .thenReturn(parceiroDTO);

        given()
                .accept(JSON)
                .when()
                .get("/parceiros/{id}", "2")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarParceiroPorLongitudeIhLatitude() {
        when(this.parceiroService.buscarPorEndereco(any(double.class), any(double.class)))
                .thenReturn(Optional.of(parceiroArapiraca));

        given().queryParam("long", 30.0)
                .queryParam("lat", 30.0)
                .get("/parceiros/proximo")
                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveCadastrarParceiroComSucesso() {
        when(this.parceiroService.cadastrar(any(Parceiro.class))).thenReturn(parceiroDTO);

        given()
                .contentType("application/json")
                .body(parceiroDTO)
                .when()
                .post("/parceiros", parceiroDTO)
                .then()
                .statusCode(201);
    }

    @Test
    public void naoDeveCadastrarParceirosDuplicados() {
        when(this.parceiroService.cadastrar(any(Parceiro.class))).thenReturn(parceiroDTO);

        when(this.parceiroRepository.findParceiroByDocument(any(String.class))).thenReturn(Optional.of(parceiroArapiraca));

        given().contentType(JSON)
                .when()
                .post("/parceiros", parceiroDTO);

        given().contentType(JSON)
                .when()
                .post("/parceiros", parceiroDTO)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }


}

