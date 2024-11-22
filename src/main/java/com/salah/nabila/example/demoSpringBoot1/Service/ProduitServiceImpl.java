package com.salah.nabila.example.demoSpringBoot1.Service;

import com.salah.nabila.example.demoSpringBoot1.modele.Produit;
import com.salah.nabila.example.demoSpringBoot1.repository.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProduitServiceImpl implements ProduitService {
    private final ProduitRepository produitRepository;

    @Override
    public Produit creer(Produit produit) {
        if (produit == null) {
            throw new IllegalArgumentException("Le produit ne peut pas être null");
        }

        return produitRepository.save(produit);
    }

    @Override
    public List<Produit> lire() {
        return produitRepository.findAll();
    }

    @Override
    public Produit modifier(Long id, Produit produit) {
        return produitRepository.findById(id).map(p->{
                           p.setNom(produit.getNom());
                           p.setDescription(produit.getDescription());
                           p.setPrix(produit.getPrix());

                           return produitRepository.save(p);}).
                orElseThrow(()->new RuntimeException("Produit "+id+ " est non trouvé "));
    }

    @Override
    public String supprimer(Long id) {
        if (!produitRepository.existsById(id)) {
            // Si le produit n'existe pas, lancer une exception ou retourner un message d'erreur
            throw new RuntimeException("Produit avec l'ID " + id + " n'existe pas.");
        }
        produitRepository.deleteById(id);
        return "Le produit "+id+" est supprimé";
    }
}
