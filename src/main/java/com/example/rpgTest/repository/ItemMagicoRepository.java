package com.example.rpgTest.repository;

import com.example.rpgTest.model.ItemMagicoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMagicoRepository extends JpaRepository<ItemMagicoModel, Long> {
}
