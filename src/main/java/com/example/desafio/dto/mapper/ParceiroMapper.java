package com.example.desafio.dto.mapper;

import com.example.desafio.domain.Parceiro;
import com.example.desafio.dto.ParceiroDTO;
import org.springframework.stereotype.Component;

@Component
public class ParceiroMapper {

    public static ParceiroDTO toDTO(Parceiro parceiro) {
        return ParceiroDTO.builder()
                .tradingName(parceiro.getTradingName())
                .ownerName(parceiro.getOwnerName())
                .document(parceiro.getDocument())
                .coverageArea(parceiro.getCoverageArea())
                .address(parceiro.getAddres())
                .build();

    }

    public static Parceiro toEntity(ParceiroDTO parceiroDTO) {
        return Parceiro.builder()
                .tradingName(parceiroDTO.getTradingName())
                .ownerName(parceiroDTO.getOwnerName())
                .document(parceiroDTO.getDocument())
                .coverageArea(parceiroDTO.getCoverageArea())
                .addres(parceiroDTO.getAddress())
                .build();
    }


}
