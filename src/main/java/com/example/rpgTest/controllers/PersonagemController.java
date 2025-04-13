package com.example.rpgTest.controllers;

import com.example.rpgTest.model.PersonagemModel;
import com.example.rpgTest.services.PersonagemService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personagem")
public class PersonagemController {
    @Autowired
    private PersonagemService personagemService;

    @Operation(summary = "Cadastrar um novo personagem")
    @PostMapping("/cadastrar-personagem")
    public ResponseEntity<PersonagemModel> createPersonagem(@RequestBody PersonagemModel personagemModel){
        return personagemService.createPersonagem(personagemModel);
    }

    @Operation(summary = "Listar todos os personagens")
    @GetMapping("/listar-personagens")
    public ResponseEntity<List<PersonagemModel>> getAllPersonagens(){
        return personagemService.getAllPersonagens();
    }

    @Operation(summary = "Listar personagem pelo ID")
    @GetMapping("/listar-personagem-por-id/{id}")
    public ResponseEntity<Optional<PersonagemModel>> getPersonagemById(@PathVariable Long id){
        return personagemService.getPersonagemById(id);
    }

    @Operation(summary = "Deletar personagem por ID")
    @DeleteMapping("/deletar-personagem-por-id/{id}")
    public ResponseEntity<?> deletePersonagemById(@PathVariable Long id){
        return personagemService.deletePersonagemById(id);
    }

    @Operation(summary = "Atualizar o personagem por ID")
    @PutMapping("/update-personagem-por-id/{id}")
    public ResponseEntity<PersonagemModel> updatePersonagemById(@RequestBody PersonagemModel personagemModel, @PathVariable Long id){
        return personagemService.updatePersonagemById(personagemModel, id);
    }
}
