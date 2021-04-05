package com.example.desafio.dto.mapper;

import com.example.desafio.domain.Addres;
import com.example.desafio.domain.CoverageArea;
import com.example.desafio.domain.Parceiro;
import com.example.desafio.dto.AddressDTO;
import com.example.desafio.dto.MultiPolygonDTO;
import com.example.desafio.dto.ParceiroDTO;
import org.springframework.stereotype.Component;

@Component
public class ParceiroMapper {

    public static Parceiro toEntity(ParceiroDTO parceiroDTO) {
        CoverageArea coverageArea = CoverageArea.fromJson(parceiroDTO.coverageArea.toJson());
        Addres addres = Addres.fromJson(parceiroDTO.getAddress().toJson());

       System.out.println(coverageArea.toString());

        return Parceiro.builder()
                .tradingName(parceiroDTO.getTradingName())
                .ownerName(parceiroDTO.getOwnerName())
                .document(parceiroDTO.getDocument())
                .coverageArea(coverageArea)
                .addres(addres)
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
