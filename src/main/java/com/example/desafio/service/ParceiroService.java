package com.example.desafio.service;

import com.example.desafio.dto.ParceiroDTO;
import org.springframework.stereotype.Service;

@Service
public interface ParceiroService {

    ParceiroDTO cadastrar(ParceiroDTO parceiroDTO);
}
