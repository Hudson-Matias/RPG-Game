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
public class ItensMagicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeItem;
    private Integer forca;
    private Integer defesa;

    private TipoArma tipoArma;

}
