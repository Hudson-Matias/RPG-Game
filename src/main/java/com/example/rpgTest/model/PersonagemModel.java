package com.example.rpgTest.model;

import com.example.rpgTest.enums.ClassesPersonagem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personagem")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nomePessoa;
    private String nomeAventureiro;
    private Integer nivel;
    private Integer forca;
    private Integer defesa;

    private ClassesPersonagem classesPersonagem;

}
