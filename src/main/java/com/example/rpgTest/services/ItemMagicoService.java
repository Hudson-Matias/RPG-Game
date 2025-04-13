package com.example.rpgTest.services;

import com.example.rpgTest.enums.TipoArma;
import com.example.rpgTest.model.ItemMagicoModel;
import com.example.rpgTest.model.PersonagemModel;
import com.example.rpgTest.repository.ItemMagicoRepository;
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
public class ItemMagicoService {

    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    @Autowired
    private PersonagemRepository personagemRepository;

    public ResponseEntity<ItemMagicoModel> createItemMagico(@RequestBody ItemMagicoModel itemMagicoModel){
        ItemMagicoModel novoItemMagico = itemMagicoRepository.save(itemMagicoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoItemMagico);
    }

    public ResponseEntity<List<ItemMagicoModel>> getAllItemMagico(){
        List<ItemMagicoModel> allItemMagico = itemMagicoRepository.findAll();
        return ResponseEntity.ok(allItemMagico);
    }

    public ResponseEntity<Optional<ItemMagicoModel>> getItemMagicoById(@PathVariable Long id){
        Optional<ItemMagicoModel> itemMagicoById = itemMagicoRepository.findById(id);
        return ResponseEntity.ok(itemMagicoById);
    }

    public ResponseEntity<?> deleteItemMagicoById(@PathVariable Long id){
        itemMagicoRepository.deleteById(id);
        return ResponseEntity.ok("Deletado com Sucesso");
    }

    public ResponseEntity<ItemMagicoModel> updateItemMagicoById(@RequestBody ItemMagicoModel updateItemMagico, @PathVariable Long id){
        return itemMagicoRepository.findById(id).map(itemMagicoModel -> {
            itemMagicoModel.setNomeItem(updateItemMagico.getNomeItem());
            itemMagicoModel.setTipoArma(updateItemMagico.getTipoArma());
            itemMagicoModel.setDefesa(updateItemMagico.getDefesa());
            itemMagicoModel.setForca(updateItemMagico.getForca());

            ItemMagicoModel updatedItemMagico = itemMagicoRepository.save(itemMagicoModel);
            return ResponseEntity.ok(updatedItemMagico);
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public ResponseEntity<?> adicionarItemAoPersonagem(Long personagemId, ItemMagicoModel item) {
        Optional<PersonagemModel> personagemOpt = personagemRepository.findById(personagemId);
        if (personagemOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem não encontrado");
        }

        PersonagemModel personagem = personagemOpt.get();

        if (item.getForca() == 0 && item.getDefesa() == 0) {
            return ResponseEntity.badRequest().body("Item não pode ter força e defesa iguais a zero");
        }

        switch (item.getTipoArma()) {
            case ARMA:
                if (item.getDefesa() > 0) return ResponseEntity.badRequest().body("Arma não pode ter defesa");
                break;
            case ARMADURA:
                if (item.getForca() > 0) return ResponseEntity.badRequest().body("Armadura não pode ter força");
                break;
            case AMULETO:
                boolean possuiAmuleto = itemMagicoRepository.findAll().stream()
                        .anyMatch(i -> i.getTipoArma() == com.example.rpgTest.enums.TipoArma.AMULETO &&
                                i.getPersonagem() != null &&
                                i.getPersonagem().getId().equals(personagemId));
                if (possuiAmuleto) return ResponseEntity.badRequest().body("Personagem já possui um amuleto");
                break;
        }

        item.setPersonagem(personagem);
        itemMagicoRepository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    public ResponseEntity<?> getAmuletoDoPersonagem(Long personagemId) {
        List<ItemMagicoModel> amuletos = itemMagicoRepository.findByPersonagemIdAndTipoArma(personagemId, TipoArma.AMULETO);

        if (amuletos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem não possui amuleto.");
        }

        return ResponseEntity.ok(amuletos.get(0)); // Considerando só um amuleto permitido
    }

}
