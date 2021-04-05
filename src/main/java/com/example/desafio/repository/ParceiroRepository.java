package com.example.desafio.repository;

import com.example.desafio.domain.Parceiro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ParceiroRepository extends MongoRepository<Parceiro, String> {

    @Override
    Optional<Parceiro> findById(String s);
}
