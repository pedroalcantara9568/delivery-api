package com.example.desafio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class ParceiroDTO implements Serializable {

    private String id;

    @NotBlank
    private String tradingName;

    @NotBlank
    private String ownerName;

    @NotBlank
    private String document;

    public MultiPolygonDTO coverageArea;

    public AddressDTO address;
}
