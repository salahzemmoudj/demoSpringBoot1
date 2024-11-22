package com.salah.nabila.example.demoSpringBoot1.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name="Produit")
@Setter
@Getter
@NoArgsConstructor
public class Produit {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length=200)
    private String nom;

    @Column(length = 100)
    private String description;


    private Integer prix;



}
