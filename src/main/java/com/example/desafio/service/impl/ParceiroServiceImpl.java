package com.example.desafio.service.impl;

import com.example.desafio.domain.Parceiro;
import com.example.desafio.dto.ParceiroDTO;
import com.example.desafio.exception.DocumentoDuplicadoException;
import com.example.desafio.repository.ParceiroRepository;
import com.example.desafio.service.ParceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.desafio.dto.mapper.ParceiroMapper.toDTO;

@Service
@Transactional
public class ParceiroServiceImpl implements ParceiroService {

    @Autowired
    ParceiroRepository parceiroRepository;

    public ParceiroDTO cadastrar(Parceiro parceiro) {
        return toDTO(parceiroRepository.save(parceiro));
    }
}
