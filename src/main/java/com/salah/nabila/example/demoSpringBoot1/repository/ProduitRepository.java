package com.salah.nabila.example.demoSpringBoot1.repository;

import com.salah.nabila.example.demoSpringBoot1.modele.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
