package com.example.rpgTest.model;


import com.example.rpgTest.enums.TipoArma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "itens_magicos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemMagicoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nomeItem;
    private Integer forca;
    private Integer defesa;

    private TipoArma tipoArma;

}
