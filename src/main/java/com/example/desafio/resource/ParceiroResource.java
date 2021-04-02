package com.example.desafio.resource;

import com.example.desafio.dto.ParceiroDTO;
import com.example.desafio.service.ParceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/parceiros")
public class ParceiroResource {

    final private ParceiroService parceiroService;

    @Autowired
    public ParceiroResource(ParceiroService parceiroService) {
        this.parceiroService = parceiroService;
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarParceiro(@RequestBody @Valid ParceiroDTO parceiroDTO) {
        return new ResponseEntity(parceiroService.cadastrar(parceiroDTO), HttpStatus.OK);
    }
}
