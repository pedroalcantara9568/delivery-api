package com.example.desafio.service;

import com.example.desafio.domain.Parceiro;
import com.example.desafio.dto.ParceiroDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ParceiroService {

    ParceiroDTO cadastrar(Parceiro parceiro);

    ParceiroDTO buscarPorId(String id);

    Optional<Parceiro> buscarPorEndereco(double lng, double ltd);
}
