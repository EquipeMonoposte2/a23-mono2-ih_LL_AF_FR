package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@SpringBootTest(classes = UtilisateursService.class)
class UtilisateursServiceTest {

    @Mock
    private Repo_Utilisateur utilisateurRepo;

    @InjectMocks
    private UtilisateursService utilisateursService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validationCreationUtilisateur() {
        //Arrange
        LocalDate dateNaissance = LocalDate.now();
        String nomUtilisateur = "John";
        String identifiant = "john123";
        Type type = Type.Utilisateur;

        //mock de la repo
        //when(utilisateurRepo.findFirstByIdentifiant(identifiant)).thenReturn(null);

        //Act
        boolean b = utilisateursService.validationCreationUtilisateur(dateNaissance, type, nomUtilisateur, identifiant);

        //Assert
        assertTrue(b);

        //valide que la repo a été appelé pour aller chercher valeur (repo appelé une fois)
        //verify(utilisateurRepo, times(1)).findFirstByIdentifiant(identifiant);
    }

    @Test
    void sauvegarderUtilisateur() {
        //Arrange
        Utilisateur utilisateur = new Utilisateur();

        //Act
        utilisateursService.sauvegarderUtilisateur(utilisateur);

        //Assert ( valide que la repo a été appelé pour save ) (repo appelé une fois)
        verify(utilisateurRepo, times(1)).save(utilisateur);
    }

    @Test
    void surpprimerUtilisateur() {
        //Arrange
        Utilisateur utilisateur = new Utilisateur();

        //Act
        utilisateursService.surpprimerUtilisateur(utilisateur);

        //Assert  ( valide que la repo a été appelé pour delete ) (repo appelé une fois)
        verify(utilisateurRepo, times(1)).delete(utilisateur);
    }

}