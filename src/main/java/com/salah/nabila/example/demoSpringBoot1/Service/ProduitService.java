package com.salah.nabila.example.demoSpringBoot1.Service;

import com.salah.nabila.example.demoSpringBoot1.modele.Produit;

import java.util.List;

public interface ProduitService {


    Produit  creer(Produit produit);


     List <Produit> lire();


     Produit modifier(Long id, Produit  produit);


     String supprimer (Long id);






}
