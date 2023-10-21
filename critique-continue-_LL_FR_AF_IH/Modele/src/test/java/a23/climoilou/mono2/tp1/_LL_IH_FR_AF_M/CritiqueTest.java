package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CritiqueTest
{

    @Test
    void ajouterJeu_AgrandiListe()
    {
        //Arrange
        Critique critique = Critique.builder().critiqueLienProduits(new ArrayList<>()).build();
        Produit produit1 = Produit.builder().nom("Pokemon").build();
        Produit produit2 = Produit.builder().nom("Smash").build();
        Produit produit3 = Produit.builder().nom("Mario").build();

        //Act
        critique.ajouterJeu(produit1,EnumEcart.FAIBLE,false);
        critique.ajouterJeu(produit2,EnumEcart.FAIBLE,true);
        critique.ajouterJeu(produit3,EnumEcart.FAIBLE,false);

        //Assert
        assertEquals(3,critique.getCritiqueLienProduits().size());
    }

    @Test
    void ajouterJeu_StockOrdre()
    {
        //Arrange
        Critique critique = Critique.builder().critiqueLienProduits(new ArrayList<>()).build();
        Produit produit1 = Produit.builder().nom("Pokemon").build();
        Produit produit2 = Produit.builder().nom("Smash").build();
        Produit produit3 = Produit.builder().nom("Mario").build();

        //Act
        critique.ajouterJeu(produit1,EnumEcart.FAIBLE,false);
        critique.ajouterJeu(produit2,EnumEcart.FAIBLE,true);
        critique.ajouterJeu(produit3,EnumEcart.FAIBLE,false);

        //Assert
        assertEquals(produit1,critique.getCritiqueLienProduits().stream().filter(e -> e.getOrdreListe() == 1).findFirst().get().getProduitActuel());
        assertEquals(produit2,critique.getCritiqueLienProduits().stream().filter(e -> e.getOrdreListe() == 2).findFirst().get().getProduitActuel());
        assertEquals(produit3,critique.getCritiqueLienProduits().stream().filter(e -> e.getOrdreListe() == 3).findFirst().get().getProduitActuel());
    }

    @Test
    void possedeNeutre_AvecNeutre_DonneTrue()
    {
        //Arrange
        Critique critique = Critique.builder().critiqueLienProduits(new ArrayList<>()).build();
        Produit produit1 = Produit.builder().nom("Pokemon").build();
        Produit produit2 = Produit.builder().nom("Smash").build();
        Produit produit3 = Produit.builder().nom("Mario").build();

        //Act
        critique.ajouterJeu(produit1,EnumEcart.FAIBLE,false);
        critique.ajouterJeu(produit2,EnumEcart.FAIBLE,true);
        critique.ajouterJeu(produit3,EnumEcart.FAIBLE,false);

        //Assert
        assertTrue(critique.possedeNeutre());
    }

    @Test
    void possedeNeutre_SansNeutre_DonneFalse()
    {
        //Arrange
        Critique critique = Critique.builder().critiqueLienProduits(new ArrayList<>()).build();
        Produit produit1 = Produit.builder().nom("Pokemon").build();
        Produit produit2 = Produit.builder().nom("Smash").build();
        Produit produit3 = Produit.builder().nom("Mario").build();

        //Act
        critique.ajouterJeu(produit1,EnumEcart.FAIBLE,false);
        critique.ajouterJeu(produit2,EnumEcart.FAIBLE,false);
        critique.ajouterJeu(produit3,EnumEcart.FAIBLE,false);

        //Assert
        assertFalse(critique.possedeNeutre());
    }

    @Test
    void possedeJeu_AvecJeuPresent_DonneTrue()
    {
        //Arrange
        Critique critique = Critique.builder().critiqueLienProduits(new ArrayList<>()).build();
        Produit produit1 = Produit.builder().nom("Pokemon").id(1l).build();
        Produit produit2 = Produit.builder().nom("Smash").id(2l).build();
        Produit produit3 = Produit.builder().nom("Mario").id(3l).build();

        //Act
        critique.ajouterJeu(produit1,EnumEcart.FAIBLE,true);
        critique.ajouterJeu(produit2,EnumEcart.FAIBLE,false);
        critique.ajouterJeu(produit3,EnumEcart.FAIBLE,false);

        //Assert
        assertTrue(critique.possedeJeu(produit1));
        assertTrue(critique.possedeJeu(produit2));
    }

    @Test
    void possedeJeu_AvecJeuNonPresent_DonneFalse()
    {
        //Arrange
        Critique critique = Critique.builder().critiqueLienProduits(new ArrayList<>()).build();
        Produit produit1 = Produit.builder().nom("Pokemon").id(1l).build();
        Produit produit2 = Produit.builder().nom("Smash").id(2l).build();
        Produit produit3 = Produit.builder().nom("Mario").id(3l).build();
        Produit produit4 = Produit.builder().nom("Pac Man").id(4l).build();

        //Act
        critique.ajouterJeu(produit1,EnumEcart.FAIBLE,false);
        critique.ajouterJeu(produit2,EnumEcart.FAIBLE,false);
        critique.ajouterJeu(produit3,EnumEcart.FAIBLE,true);

        //Assert
        assertFalse(critique.possedeJeu(produit4));
    }

    @Test
    void possedeJeu_AvecJeuPresentAutreProduitMemeId_DonneTrue()
    {
        //Arrange
        Critique critique = Critique.builder().critiqueLienProduits(new ArrayList<>()).build();
        Produit produit1 = Produit.builder().nom("Pokemon").id(1l).build();
        Produit produit2 = Produit.builder().nom("Smash").id(2l).build();
        Produit produit3 = Produit.builder().nom("Mario").id(3l).build();
        Produit produit4 = Produit.builder().nom("Pokemon").id(1l).build();

        //Act
        critique.ajouterJeu(produit1,EnumEcart.FAIBLE,false);
        critique.ajouterJeu(produit2,EnumEcart.FAIBLE,true);
        critique.ajouterJeu(produit3,EnumEcart.FAIBLE,false);

        //Assert
        assertTrue(critique.possedeJeu(produit4));
    }
}