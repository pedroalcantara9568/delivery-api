package com.example.desafio.dto.mapper;

import com.example.desafio.domain.Parceiro;
import com.example.desafio.dto.ParceiroDTO;
import com.mapbox.services.commons.geojson.MultiPolygon;
import com.mapbox.services.commons.geojson.Point;
import com.mapbox.turf.TurfJoins;
import org.springframework.stereotype.Component;

@Component
public class ParceiroMapper {

    public static Parceiro toEntity(ParceiroDTO parceiroDTO) {
        double[][][][] coverageAreaCoordinates = parceiroDTO.coverageArea.getCoordinates();

        MultiPolygon multiPolygon = MultiPolygon.fromCoordinates(coverageAreaCoordinates);
        Point point = Point.fromCoordinates(parceiroDTO.address.getCoordinates());

//        String s = multiPolygon.toJson();
//
//        String s1 = point.toJson();
//
//        com.mapbox.geojson.MultiPolygon multiPolygon1 = com.mapbox.geojson.MultiPolygon.fromJson(s);
//
//        com.mapbox.geojson.Point point1 = com.mapbox.geojson.Point.fromJson(s1);
//
//        boolean inside = TurfJoins.inside(point1, multiPolygon1);

        return Parceiro.builder()
                .tradingName(parceiroDTO.getTradingName())
                .ownerName(parceiroDTO.getOwnerName())
                .document(parceiroDTO.getDocument())
                .coverageArea(multiPolygon)
                .addres(point)
                .build();
    }

    public static ParceiroDTO toDTO(Parceiro parceiro) {
        return ParceiroDTO.builder()
                .tradingName(parceiro.getTradingName())
                .ownerName(parceiro.getOwnerName())
                .document(parceiro.getDocument())
//                .address(parceiro.getAddres())
                .build();
    }


}
