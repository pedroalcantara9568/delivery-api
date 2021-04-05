package com.example.desafio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class ParceiroDTO implements Serializable {

    private String id;

    private String tradingName;

    private String ownerName;

    private String document;

    public MultiPolygonDTO coverageArea;

    public AddressDTO address;
}
