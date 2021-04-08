package com.example.desafio.dto.mapper;

import com.example.desafio.domain.Addres;
import com.example.desafio.dto.AddressDTO;

public class AddresMapper {

    public static Addres toEntity(AddressDTO addressDTO) {
        return new Addres(addressDTO.getType(), addressDTO.getCoordinates());
    }

    public static AddressDTO toDTO(Addres address) {
        return new AddressDTO(address.getType(), address.getCoordinates());
    }
}
