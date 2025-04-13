package com.example.rpgTest.model;

import com.example.rpgTest.enums.ClassesPersonagem;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    @Min(0)
    @Max(10)
    private Integer forca;
    @Min(0)
    @Max(10)
    private Integer defesa;

    private ClassesPersonagem classesPersonagem;

}
