package com.example.rpgTest.repository;

import com.example.rpgTest.enums.TipoArma;
import com.example.rpgTest.model.ItemMagicoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMagicoRepository extends JpaRepository<ItemMagicoModel, Long> {
    List<ItemMagicoModel> findByPersonagemIdAndTipoArma(Long personagemId, TipoArma tipoArma);
}
