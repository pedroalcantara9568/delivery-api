package com.example.desafio.domain;

import com.mapbox.geojson.Point;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "parceiros")
public class Parceiro implements Serializable {

    @Id
    private String id;

    private String tradingName;

    private String ownerName;

    private String document;

    private CoverageArea coverageArea;

    private Addres addres;

    public Parceiro(Addres addres, CoverageArea coverageArea, String document, String ownerName, String tradingName) {
        this.addres = addres;
        this.coverageArea = coverageArea;
        this.document = document;
        this.ownerName = ownerName;
        this.tradingName = tradingName;
    }

    public String getId() {
        return id;
    }

    public String getTradingName() {
        return tradingName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getDocument() {
        return document;
    }

    public CoverageArea getCoverageArea() {
        return coverageArea;
    }

    public Addres getAddres() {
        return addres;
    }

    public boolean atendeRegiao(Addres addres) {
        return this.coverageArea.estaContido(addres);
    }

    public double distanciaDe(Point pontoReferencia) {
        Point point = Point.fromJson(this.getAddres().toJson());
        return this.getCoverageArea().distanciaEmCentimetros(point, pontoReferencia);
    }


}



