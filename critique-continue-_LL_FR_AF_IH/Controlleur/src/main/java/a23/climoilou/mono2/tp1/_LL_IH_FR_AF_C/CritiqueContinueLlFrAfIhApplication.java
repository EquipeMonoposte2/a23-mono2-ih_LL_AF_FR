package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.UtilisateursService;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Main de l'application lance l'application
 */
@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = {
        "a23.climoilou.mono2.tp1._LL_IH_FR_AF_C",
        "a23.climoilou.mono2.tp1._LL_IH_FR_AF_M",
        "a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services",
        "a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository",
        "a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs",
        "a23.climoilou.mono2.tp1._LL_IH_FR_AF_V",
        "net.rgielen.fxweaver.spring.boot.autoconfigure"})
@EntityScan(basePackages = {
        "a23.climoilou.mono2.tp1._LL_IH_FR_AF_M",
        "a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository",
        "a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services",
        "a23.climoilou.mono2.tp1._LL_IH_FR_AF"})
public class CritiqueContinueLlFrAfIhApplication implements CommandLineRunner {


    //demander à martin pourquoi ca marche pas!
    /*private final UtilisateursService utilisateursService;

    @Autowired
    public CritiqueContinueLlFrAfIhApplication(UtilisateursService utilisateursService) {
        this.utilisateursService = utilisateursService;
    }*/

    public static void main(String[] args) {
        //SpringApplication.run(CritiqueContinueLlFrAfIhApplication.class, args);
        Application.launch(ApplicationFX.class);
    }
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        /*Utilisateur utilisateur = utilisateursService.findUtilisateurByID(1l);

        if(utilisateur!=null){
            utilisateursService.createUtilisateur(Utilisateur.builder().critiqueList(new ArrayList<>()).dateDeNaissance(LocalDate.now()).type(Type.Utilisateur).nom("Tommy").id(1L).build());
        }*/
    }
}