package com.example.rpgTest.model;


import com.example.rpgTest.enums.TipoArma;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    @Min(0)
    @Max(10)
    private Integer forca;
    @Min(0)
    @Max(10)
    private Integer defesa;

    private TipoArma tipoArma;

    @ManyToOne
    @JoinColumn(name = "personagem_id")
    private PersonagemModel personagem;
}
