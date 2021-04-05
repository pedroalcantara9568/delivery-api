package com.example.desafio.resource;

import com.example.desafio.dto.ParceiroDTO;
import com.example.desafio.service.ParceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import static com.example.desafio.dto.mapper.ParceiroMapper.toEntity;

@RestController
@RequestMapping("/parceiros")
public class ParceiroResource {

    final private ParceiroService parceiroService;

    @Autowired
    public ParceiroResource(ParceiroService parceiroService) {
        this.parceiroService = parceiroService;
    }

    @PostMapping
    public ResponseEntity cadastrarParceiro(@RequestBody @Valid ParceiroDTO parceiroDTO) throws URISyntaxException {
        ParceiroDTO parceiroCadastrado = parceiroService.cadastrar(toEntity(parceiroDTO));
        return ResponseEntity.created(new URI(String.format("%s/%s", "/parceiros", parceiroCadastrado.getId()))).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParceiroDTO> buscarPorId(@PathVariable("id") String id) {
        ParceiroDTO parceiroDTO = parceiroService.buscarPorId(id);
        return ResponseEntity.ok(parceiroDTO);
    }

}
