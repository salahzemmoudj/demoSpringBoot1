package com.salah.nabila.example.demoSpringBoot1.Service;

import com.salah.nabila.example.demoSpringBoot1.modele.Produit;
import com.salah.nabila.example.demoSpringBoot1.repository.ProduitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProduitServiceImplTest {

    @Mock
   private ProduitRepository produitRepository;

   @InjectMocks
    ProduitServiceImpl produitServiceImpl;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
   void testCreer_ProduitNull() {
       Produit produit=null;
       IllegalArgumentException exception= assertThrows(IllegalArgumentException.class, () -> {produitServiceImpl.creer(null);});
       assertEquals("Le produit ne peut pas être null",exception.getMessage() );
       verify(produitRepository, times(0)).save(produit);
    }


    @Test
    void testCreer_ProduitNotNull() {
        Produit poduitInput= new Produit();
        poduitInput.setNom("Produit Test");
        poduitInput.setPrix(10);
        poduitInput.setDescription("Description Test");


        Produit ProduitSaved=new Produit();
        ProduitSaved.setId(1L);
        ProduitSaved.setNom("Produit Test");
        ProduitSaved.setPrix(10);
        ProduitSaved.setDescription("Description Test");

        when(produitRepository.save(poduitInput)).thenReturn(ProduitSaved);

      Produit produitResult= produitServiceImpl.creer(poduitInput);
      assertNotNull(produitResult);
      assertEquals(ProduitSaved.getId(), produitResult.getId());
      assertEquals(ProduitSaved.getNom(), produitResult.getNom());
      assertEquals(ProduitSaved.getDescription(), produitResult.getDescription());
      assertEquals(ProduitSaved.getPrix(), produitResult.getPrix());

      verify(produitRepository, times(1)).save(poduitInput);


    }


    @Test
    void testLire_withProducts()
    {
        Produit produit1=new Produit();
         produit1.setId(1L);
         produit1.setNom("Product 1");
         produit1.setDescription("Description Product 1");
         produit1.setPrix(10);
        Produit produit2=new Produit();
          produit2.setId(2L);
          produit2.setNom("Product 2");
          produit2.setDescription("Description Product 2");
          produit2.setPrix(100);

       List <Produit> produits= Arrays.asList(produit1,produit2);

       when(produitRepository.findAll()).thenReturn(produits);

       List<Produit> ProduitsResult= produitServiceImpl.lire();

        assertNotNull(ProduitsResult);
        assertEquals(2,ProduitsResult.size());
        assertEquals(ProduitsResult.get(0),produit1);
        assertEquals(ProduitsResult.get(1),produit2);



    }

    @Test
    void testLire_EmptyList() {
     when(produitRepository.findAll()).thenReturn(Collections.emptyList());

        List<Produit> ListProdutit= produitServiceImpl.lire();
        assertNotNull(ListProdutit);
        assertTrue(ListProdutit.isEmpty());

     verify(produitRepository, times(1)).findAll();
    }
//
//    @Test
//    void modifier() {
//    }
//




    @Test
    void testSupprimerProduitNotExistant(){
        Long ProduitId=1000L;
        when(produitRepository.existsById(ProduitId)).thenReturn(false);
        RuntimeException exception=assertThrows(RuntimeException.class,()->{produitServiceImpl.supprimer(ProduitId);});
        assertEquals("Produit avec l'ID " + ProduitId + " n'existe pas.",exception.getMessage());


        verify(produitRepository, times(0)).deleteById(ProduitId);
    }



    @Test
    void testSupprimerProduitExistant() {
        Long produitIDd=1L;
        when (produitRepository.existsById(produitIDd)).thenReturn(true);
        String message=produitServiceImpl.supprimer(1L);

        assertEquals( "Le produit "+produitIDd+" est supprimé", message);

        verify(produitRepository, times(1)).deleteById(produitIDd);

    }


@Test
    void testModifierProduitNotExist(){
        Long produitId=1000L;
        Produit produit=new Produit();
         produit.setNom("nom de produit amodifier");
         produit.setDescription("Description de produit a modifier");
         produit.setPrix(100);

        when(produitRepository.findById(produitId)).thenReturn(Optional.empty());
        RuntimeException exception=assertThrows(RuntimeException.class,()->{produitServiceImpl.modifier(produitId, produit);});

        assertEquals("Produit "+produitId+ " est non trouvé ", exception.getMessage());
        verify(produitRepository, times(1)).findById(produitId);
        verify(produitRepository, times(0)).save(any(Produit.class));

}



//@Test
//    void testModifierProduitExist(){
//       Long produitId = 1L;
//
//       Produit produitExistant  = new Produit();
//               produitExistant.setId(produitId);
//               produitExistant.setNom("nom de produit a modifier");
//               produitExistant.setDescription("Description de produit a modifier");
//               produitExistant.setPrix(100);
//
//
//      Produit produitModifie=new Produit();
//              produitModifie.setId(2L);
//              produitModifie.setNom("nom de produit modifie");
//              produitModifie.setDescription("Description de produit modifie");
//              produitModifie.setPrix(112);
//
//       when(produitRepository.findById(produitId)).thenReturn(Optional.of(produitExistant ));
//         System.out.println("id de produit= " +produitExistant.getId());
//
//         System.out.println("id de produit est il exist= "+Optional.of(produitExistant));
//
//       Produit updatedProduit = produitServiceImpl.modifier(produitId, produitModifie);
//       verify(produitRepository, times(1)).findById(produitId);
//}



    @Test
    void modifier_shouldUpdateProduit_whenProduitExists() {
        // Arrange
        Long id = 1L;
        Produit existingProduit = new Produit();
        existingProduit.setId(id);
        existingProduit.setNom("Ancien Nom");
        existingProduit.setDescription("Ancienne Description");
        existingProduit.setPrix(100);

        Produit updatedProduit = new Produit();
        updatedProduit.setNom("Nouveau Nom");
        updatedProduit.setDescription("Nouvelle Description");
        updatedProduit.setPrix(150);

        when(produitRepository.findById(id)).thenReturn(Optional.of(existingProduit));
        when(produitRepository.save(any(Produit.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Produit result = produitServiceImpl.modifier(id, updatedProduit);

        // Assert
        assertNotNull(result);
        assertEquals("Nouveau Nom", result.getNom());
        assertEquals("Nouvelle Description", result.getDescription());
        assertEquals(150, result.getPrix());

        verify(produitRepository, times(1)).findById(id);
        verify(produitRepository, times(1)).save(existingProduit);
    }



}