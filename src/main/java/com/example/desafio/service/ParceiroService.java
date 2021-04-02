package com.example.desafio.service;

import com.example.desafio.domain.Parceiro;
import com.example.desafio.dto.ParceiroDTO;
import org.springframework.stereotype.Service;

@Service
public interface ParceiroService {

    Parceiro cadastrar(ParceiroDTO parceiroDTO);
}
