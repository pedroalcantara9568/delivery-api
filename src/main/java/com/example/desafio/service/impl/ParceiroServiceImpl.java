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


    @Autowired
    ParceiroRepository parceiroRepository;


    public ParceiroDTO cadastrar(ParceiroDTO parceiroDTO) {
        Parceiro parceiroSalvo = this.parceiroRepository.save(ParceiroMapper.toEntity(parceiroDTO));
        return ParceiroMapper.toDTO(parceiroSalvo);
    }
}
