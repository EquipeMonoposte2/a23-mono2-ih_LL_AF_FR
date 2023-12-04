package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_produits;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Classe de test de ProduitsService
 */
class ProduitsServiceTest {

    @Mock   //mock la repo
    private Repo_produits produitRepository;

    @InjectMocks   //inject le mock dans le service
    private ProduitsService produitsService;

    @BeforeEach
    void setUp() {
        //initialise avant chaque test les class annoté ci-dessus (Repo_produits,ProduitsService )
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test pour valider le delete de produitservice
     */
    @Test
    void deleteProduit(){
        //Arrange
        Produit produit = new Produit();

        //Act
        produitsService.deleteProduit(produit);

        //Assert (repo appelé une fois lors de sauvegarde)
        verify(produitRepository, times(1)).delete(produit);

    }

    /**
     * Test pour valider le save de produitservice
     */
    @Test
    void saveProduit() {
        //Arrange
        Produit produit = new Produit();

        //Act
        produitsService.saveProduit(produit);

        //Assert (repo appelé une fois lors de sauvegarde)
        verify(produitRepository, times(1)).save(produit);
    }

    /**
     * Test pour tester la validation avant de sauvegarder un produit
     */
    @Test
    void creationValidationProduitValidInput() {
        //Arrange
        String nom = "Product";
        String description = "Description";
        LocalDate dateSortie = LocalDate.now();
        String image = "image.jpg";

        //Act
        Produit produit = produitsService.creationValidationProduit(nom, description, dateSortie, image, null);

        //Assert
        assertNotNull(produit);
        assertEquals(nom, produit.getNom());
        assertEquals(description, produit.getDescription());
        assertEquals(dateSortie, produit.getDateDeSortie());
        assertEquals(image, produit.getImage());

        //verifie que la repo n'a pas été appelé
        verify(produitRepository, times(0)).save(produit);
    }


}