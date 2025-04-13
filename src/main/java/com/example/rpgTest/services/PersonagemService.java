package com.example.rpgTest.services;

import com.example.rpgTest.model.PersonagemModel;
import com.example.rpgTest.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    public ResponseEntity<PersonagemModel> createPersonagem(@RequestBody PersonagemModel personagemModel){
        PersonagemModel novoPersonagem = personagemRepository.save(personagemModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPersonagem);
    }

    public ResponseEntity<List<PersonagemModel>> getAllPersonagens(){
        List<PersonagemModel> allPersonagens = personagemRepository.findAll();
        return ResponseEntity.ok(allPersonagens);
    }

    public ResponseEntity<Optional<PersonagemModel>> getPersonagemById(@PathVariable Long id){
        Optional<PersonagemModel> personagemById = personagemRepository.findById(id);
        return ResponseEntity.ok(personagemById);
    }

    public ResponseEntity<?> deletePersonagemById(@PathVariable Long id){
        personagemRepository.deleteById(id);
        return ResponseEntity.ok("Personagem deletado com sucesso");
    }

    public ResponseEntity<PersonagemModel> updatePersonagemById(@RequestBody PersonagemModel updatePersonagem, @PathVariable Long id){
        return personagemRepository.findById(id).map(personagemModel -> {
            personagemModel.setNomePessoa(updatePersonagem.getNomePessoa());
            personagemModel.setNomeAventureiro(updatePersonagem.getNomeAventureiro());
            personagemModel.setNivel(updatePersonagem.getNivel());
            personagemModel.setClassesPersonagem(updatePersonagem.getClassesPersonagem());
            personagemModel.setForca(updatePersonagem.getForca());
            personagemModel.setDefesa(updatePersonagem.getDefesa());

            PersonagemModel updatedPersonagem = personagemRepository.save(personagemModel);
            return ResponseEntity.ok(updatedPersonagem);
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
