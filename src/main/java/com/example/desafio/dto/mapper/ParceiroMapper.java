package com.example.desafio.dto.mapper;

import com.example.desafio.domain.Parceiro;
import com.example.desafio.dto.AddressDTO;
import com.example.desafio.dto.MultiPolygonDTO;
import com.example.desafio.dto.ParceiroDTO;
import com.mapbox.services.commons.geojson.MultiPolygon;
import com.mapbox.services.commons.geojson.Point;
import org.springframework.stereotype.Component;

@Component
public class ParceiroMapper {

    public static Parceiro toEntity(ParceiroDTO parceiroDTO) {
        MultiPolygon multiPolygon = MultiPolygon.fromCoordinates(parceiroDTO.coverageArea.getCoordinates());
        Point point = Point.fromCoordinates(parceiroDTO.address.getCoordinates());

        return Parceiro.builder()
                .tradingName(parceiroDTO.getTradingName())
                .ownerName(parceiroDTO.getOwnerName())
                .document(parceiroDTO.getDocument())
                .coverageArea(multiPolygon)
                .addres(point)
                .build();
    }

    public static ParceiroDTO toDTO(Parceiro parceiro) {
        MultiPolygonDTO multiPolygonDTO = MultiPolygonDTO.fromJson(parceiro.getCoverageArea().toJson());
        AddressDTO addressDTO = AddressDTO.fromJson(parceiro.getAddres().toJson());

        return ParceiroDTO.builder()
                .id(parceiro.getId())
                .tradingName(parceiro.getTradingName())
                .ownerName(parceiro.getOwnerName())
                .document(parceiro.getDocument())
                .coverageArea(multiPolygonDTO)
                .address(addressDTO)
                .build();
    }

}
