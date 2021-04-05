package com.example.desafio.repository;

import com.example.desafio.domain.Parceiro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ParceiroRepository extends MongoRepository<Parceiro, String> {

    @Query("{ 'document' : ?0}")
    Optional<Parceiro> findParceiroByDocument(String document);

}
