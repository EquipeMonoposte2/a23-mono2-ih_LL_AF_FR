package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.calcules;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.CritiqueLienProduit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.EnumEcart;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DB;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;

class CalculAppreciationTest {

    @Mock
    private DB db;
    @Mock
    private CalculesSignifiance signifiance;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void additionnerMoyennes_NotesPositives_retourOk() {
        CalculAppreciation appreciation = new CalculAppreciation(db, signifiance);

        float moyenneAmateur = 5.567f;
        float moyenneInfluenceur = 1.987f;
        float moyenneExpert = 6.1234f;
        float resultatMoyenne = 0.45f * moyenneExpert + 0.22f * moyenneAmateur + 0.33f * moyenneInfluenceur;

        float resultat = appreciation.additionnerMoyennes(moyenneAmateur, moyenneInfluenceur, moyenneExpert);

        assertEquals(resultatMoyenne, resultat);
    }

    @Test
    public void additionnerMoyennes_NotesNegatives_retourOk() {
        CalculAppreciation appreciation = new CalculAppreciation(db, signifiance);

        float moyenneAmateur = -5.567f;
        float moyenneInfluenceur = -1.987f;
        float moyenneExpert = -6.1234f;
        float resultatMoyenne = 0.45f * moyenneExpert + 0.22f * moyenneAmateur + 0.33f * moyenneInfluenceur;

        float resultat = appreciation.additionnerMoyennes(moyenneAmateur, moyenneInfluenceur, moyenneExpert);

        assertEquals(resultatMoyenne, resultat);
    }

    @Test
    public void calculerMoyenne_EntreeValide_RetourValide() {
        CalculAppreciation appreciation = new CalculAppreciation(db, signifiance);
        Map<Utilisateur, List<Map<Produit, Float>>> mockAppreciations = new HashMap<>();
        Utilisateur user = mock(Utilisateur.class);
        Produit produitActuel = mock(Produit.class);
        List<Map<Produit, Float>> userAppreciations = new ArrayList<>();
        Map<Produit, Float> appreciationMap = new HashMap<>();
        Map<Produit, Float> appreciationMap2 = new HashMap<>();
        appreciationMap.put(produitActuel, 8.0f);
        appreciationMap2.put(produitActuel, 5.0f);
        userAppreciations.add(appreciationMap);
        userAppreciations.add(appreciationMap2);
        mockAppreciations.put(user, userAppreciations);
        float expected = 6.5f;

        float resultat = appreciation.calculerMoyenne(mockAppreciations, produitActuel);

        assertEquals(expected, resultat);
    }

    @Test
    public void diviserAppreciation_retourValide() {
        CalculAppreciation appreciation = new CalculAppreciation(db, signifiance);
        Map<Produit, Float> produitAppreciation = new HashMap<>();
        Produit produit1 = mock(Produit.class);
        Produit produit2 = mock(Produit.class);
        Produit produit3 = mock(Produit.class);
        Produit produit4 = mock(Produit.class);
        produitAppreciation.put(produit1, 10.0f);
        produitAppreciation.put(produit2, -5.0f);
        produitAppreciation.put(produit3, 3.0f);
        produitAppreciation.put(produit4, -2.0f);

        appreciation.diviserAppreciation(produitAppreciation);

        assertEquals(1.0f, produitAppreciation.get(produit1));
        assertEquals(-1.0f, produitAppreciation.get(produit2));
        assertEquals(0.3f, produitAppreciation.get(produit3));
        assertEquals(-0.4f, produitAppreciation.get(produit4));
    }

    @Test
    public void obtenirMapPointageCalcule_retourValide() {
        CalculAppreciation appreciation = new CalculAppreciation(db, signifiance);
        List<CritiqueLienProduit> critiqueLienProduits = new ArrayList<>();
        for (int[] i = {0}; i[0] < 4; i[0]++) {
            CritiqueLienProduit critiqueLienProduit = mock(CritiqueLienProduit.class);
            Produit mockedProduit = mock(Produit.class);
            Mockito.when(critiqueLienProduit.getEcart()).then(ecart -> EnumEcart.NORMAL);
            Mockito.when(critiqueLienProduit.getProduitActuel()).thenReturn(mockedProduit);
            critiqueLienProduits.add(critiqueLienProduit);
        }

        int indiceNeutre = 3;

        HashMap<Produit, Float> result = appreciation.obtenirMapPointageCalcule(critiqueLienProduits, indiceNeutre);

        assertEquals(4, result.size());

        Produit neutralProduit = critiqueLienProduits.get(indiceNeutre).getProduitActuel();
        Produit produitNegatif = critiqueLienProduits.get(0).getProduitActuel();
        assertEquals(0f, result.get(neutralProduit));
        assertEquals(-6f, result.get(produitNegatif));
    }

    @Test
    public void pointagePositif_retourValide() {
        CalculAppreciation appreciation = new CalculAppreciation(db, signifiance);
        Produit produit1 = mock(Produit.class);
        Produit produit2 = mock(Produit.class);

        CritiqueLienProduit critique1 = mock(CritiqueLienProduit.class);
        CritiqueLienProduit critique2 = mock(CritiqueLienProduit.class);

        Mockito.when(critique1.getProduitActuel()).thenReturn(produit1);
        Mockito.when(critique2.getProduitActuel()).thenReturn(produit2);

        Mockito.when(critique1.getEcart()).thenReturn(EnumEcart.FAIBLE);
        Mockito.when(critique2.getEcart()).thenReturn(EnumEcart.GRAND);

        List<CritiqueLienProduit> critiques = List.of(critique1, critique2);

        HashMap<Produit, Float> mockedMap = new HashMap<>();
        mockedMap.put(produit1, 1f);
        mockedMap.put(produit2, 4f);


        int indiceNeutre = 0;
        appreciation.pointagePositif(critiques, indiceNeutre, mockedMap);

        assertEquals(1f, mockedMap.get(produit1));
        assertEquals(3f, mockedMap.get(produit2));
    }

    @Test
    public void pointageNegatif_retourValide() {
        CalculAppreciation appreciation = new CalculAppreciation(db, signifiance);
        Produit produit1 = mock(Produit.class);
        Produit produit2 = mock(Produit.class);

        CritiqueLienProduit critique1 = mock(CritiqueLienProduit.class);
        CritiqueLienProduit critique2 = mock(CritiqueLienProduit.class);

        Mockito.when(critique1.getProduitActuel()).thenReturn(produit1);
        Mockito.when(critique2.getProduitActuel()).thenReturn(produit2);

        Mockito.when(critique1.getEcart()).thenReturn(EnumEcart.FAIBLE);
        Mockito.when(critique2.getEcart()).thenReturn(EnumEcart.GRAND);

        List<CritiqueLienProduit> critiques = List.of(critique1, critique2);

        HashMap<Produit, Float> mockedMap = new HashMap<>();
        mockedMap.put(produit1, 1f);
        mockedMap.put(produit2, 4f);


        int indiceNeutre = mockedMap.size() - 1;
        appreciation.pointagePositif(critiques, indiceNeutre, mockedMap);

        assertEquals(1f, mockedMap.get(produit1));
        assertEquals(4f, mockedMap.get(produit2));
    }


}