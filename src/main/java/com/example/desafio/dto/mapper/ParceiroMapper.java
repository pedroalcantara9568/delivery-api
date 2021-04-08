package com.example.desafio.dto.mapper;

import com.example.desafio.domain.Addres;
import com.example.desafio.domain.CoverageArea;
import com.example.desafio.domain.Parceiro;
import com.example.desafio.dto.AddressDTO;
import com.example.desafio.dto.CoverageAreaDTO;
import com.example.desafio.dto.ParceiroDTO;
import org.springframework.stereotype.Component;

@Component
public class ParceiroMapper {

    public static Parceiro toEntity(ParceiroDTO parceiroDTO) {
        Addres addres = AddresMapper.toEntity(parceiroDTO.address);
        CoverageArea coverageArea = CoverageAreaMapper.toEntity(parceiroDTO.coverageArea);

        return Parceiro.builder()
                .tradingName(parceiroDTO.getTradingName())
                .ownerName(parceiroDTO.getOwnerName())
                .document(parceiroDTO.getDocument())
                .coverageArea(coverageArea)
                .addres(addres)
                .build();
    }

    public static ParceiroDTO toDTO(Parceiro parceiro) {
        AddressDTO addressDTO = AddresMapper.toDTO(parceiro.getAddres());
        CoverageAreaDTO coverageAreaDTO = CoverageAreaMapper.toDTO(parceiro.getCoverageArea());

        return ParceiroDTO.builder()
                .id(parceiro.getId())
                .tradingName(parceiro.getTradingName())
                .ownerName(parceiro.getOwnerName())
                .document(parceiro.getDocument())
                .coverageArea(coverageAreaDTO)
                .address(addressDTO)
                .build();
    }

}
