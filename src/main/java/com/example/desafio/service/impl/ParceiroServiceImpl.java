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
    private final ParceiroRepository parceiroRepository;

    public ParceiroServiceImpl(ParceiroRepository parceiroRepository) {
        this.parceiroRepository = parceiroRepository;
    }

    public ParceiroDTO cadastrar(Parceiro parceiro) {
        parceiroRepository.findParceiroByDocument(parceiro.getDocument()).ifPresent(parceiroConsultado -> {
            throw new DocumentoDuplicadoException("");
        });
        return toDTO(parceiroRepository.save(parceiro));
    }

    @Override
    public ParceiroDTO buscarPorId(String id) {
        Optional<Parceiro> parceiroConsultado = parceiroRepository.findById(id);
        if (parceiroConsultado.isPresent()) {
            return toDTO(parceiroConsultado.get());
        }
        return null;
    }
}
