package com.example.desafio.domain;

import com.mapbox.services.commons.geojson.MultiPolygon;
import com.mapbox.services.commons.geojson.Point;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Builder
@Document(collection = "parceiros")
public class Parceiro implements Serializable {

    @Id
    private String id;

    private String tradingName;

    private String ownerName;

    private String document;

    private CoverageArea coverageArea;

    private Addres addres;

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
}



