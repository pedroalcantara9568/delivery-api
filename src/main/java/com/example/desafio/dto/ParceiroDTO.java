package com.example.desafio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParceiroDTO implements Serializable {

    private String id;

    private String tradingName;

    private String ownerName;

    private String document;

    public CoverageAreaDTO coverageArea;

    public AddressDTO address;

}
