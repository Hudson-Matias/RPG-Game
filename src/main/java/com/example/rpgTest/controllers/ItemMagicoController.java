package com.example.rpgTest.controllers;

import com.example.rpgTest.model.ItemMagicoModel;
import com.example.rpgTest.services.ItemMagicoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item-magico")
public class ItemMagicoController {

    @Autowired
    private ItemMagicoService itemMagicoService;

    @Operation(summary = "Cadastrar um novo item mágico")
    @PostMapping("/cadastrar-item-magico")
    public ResponseEntity<ItemMagicoModel> createItemMagico(@RequestBody ItemMagicoModel itemMagicoModel){
        return itemMagicoService.createItemMagico(itemMagicoModel);
    }

    @Operation(summary = "Listar todos os itens mágicos")
    @GetMapping("/lista-item-magico")
    public ResponseEntity<List<ItemMagicoModel>> getAllItemMagico(){
        return itemMagicoService.getAllItemMagico();
    }

    @Operation(summary = "Listar item mágico pelo ID")
    @GetMapping("/lista-item-magico-por-id/{id}")
    public ResponseEntity<Optional<ItemMagicoModel>> getItemMagicoById(@PathVariable Long id){
        return itemMagicoService.getItemMagicoById(id);
    }

    @Operation(summary = "Deletar item mágico por ID")
    @DeleteMapping("/deletar-item-magico-por-id/{id}")
    public ResponseEntity<?> deleteItemMagicoById(@PathVariable Long id){
        return itemMagicoService.deleteItemMagicoById(id);
    }

    @Operation(summary = "Atualizar item mágico por ID")
    @PutMapping("/update-item-magico-por-id/{id}")
    public ResponseEntity<ItemMagicoModel> updateItemMagicoById(@RequestBody ItemMagicoModel itemMagicoModel, @PathVariable Long id){
        return itemMagicoService.updateItemMagicoById(itemMagicoModel, id);
    }
}
