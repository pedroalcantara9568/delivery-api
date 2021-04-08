package com.example.desafio.dto.mapper;

import com.example.desafio.domain.CoverageArea;
import com.example.desafio.dto.CoverageAreaDTO;

public class CoverageAreaMapper {

    public static CoverageArea toEntity(CoverageAreaDTO coverageAreaDTO) {
        return new CoverageArea(coverageAreaDTO.getType(), coverageAreaDTO.getCoordinates());
    }

    public static CoverageAreaDTO toDTO(CoverageArea coverageArea) {
        return new CoverageAreaDTO(coverageArea.getType(), coverageArea.getCoordinates());
    }


}
