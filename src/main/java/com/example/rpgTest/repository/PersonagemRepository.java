package com.example.rpgTest.repository;

import com.example.rpgTest.model.PersonagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonagemRepository extends JpaRepository<PersonagemModel, Long> {
}
