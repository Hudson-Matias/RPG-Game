package com.example.rpgTest.services;

import com.example.rpgTest.model.ItemMagicoModel;
import com.example.rpgTest.repository.ItemMagicoRepository;
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
}
