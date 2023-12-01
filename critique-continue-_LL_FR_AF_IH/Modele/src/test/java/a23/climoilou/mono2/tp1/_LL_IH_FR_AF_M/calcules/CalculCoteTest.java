package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.calcules;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculCoteTest {
    @Mock
    private CalculesSignifiance signifiance;
    @Mock
    private CalculAppreciation appreciation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void calculerMoyenne_retourOK(){
        float resultatAppreciation = 8.123f;
        float resultatSignifiance = 6.456f;
        float resultatCote = 5 + resultatAppreciation * resultatSignifiance * 5;

        Mockito.when(signifiance.calculeSignifiance(Mockito.any(Produit.class))).then(resultat -> resultatSignifiance);
        Mockito.when(appreciation.calculeAppreciation(Mockito.any(Produit.class))).then(resultat -> resultatAppreciation);
        CalculCote cote = new CalculCote(signifiance, appreciation);

        float resultat = cote.calculerCoteFinale(new Produit());

        assertEquals(resultatCote, resultat);
    }
}