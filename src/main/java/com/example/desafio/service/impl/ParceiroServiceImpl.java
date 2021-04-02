package com.example.desafio.service.impl;

import com.example.desafio.domain.Parceiro;
import com.example.desafio.dto.ParceiroDTO;
import com.example.desafio.dto.mapper.ParceiroMapper;
import com.example.desafio.repository.ParceiroRepository;
import com.example.desafio.service.ParceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParceiroServiceImpl implements ParceiroService {

    private final ParceiroRepository parceiroRepository;

    @Autowired
    public ParceiroServiceImpl(ParceiroRepository parceiroRepository) {
        this.parceiroRepository = parceiroRepository;
    }

    public Parceiro cadastrar(ParceiroDTO parceiroDTO) {
        Parceiro parceiro = ParceiroMapper.toEntity(parceiroDTO);
        return this.parceiroRepository.save(parceiro);
    }
}
