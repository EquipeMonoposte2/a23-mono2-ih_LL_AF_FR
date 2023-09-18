package a23.climoilou.mono2.tp1._LL_IH_FR_AF_C.vuecontroleurs;


import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.DataBase;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services.UtilisateursService;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_critique;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 */
@FxmlView("ConnectionVue.fxml")
@Component
public class ConnectionControleur {

    private final ApplicationEventPublisher applicationEventPublisher;

    public ConnectionControleur(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * Lance un évenement de type connection si l'utilisateur est valide ( avec applicationEventPublisher) sera entendu (ConfigurableApplicationContext context) de ApplicationFx
     * @param event
     */
    @FXML
    void connect(ActionEvent event) {
        //condition pour valider la connection
        //user temporaire
        Utilisateur utilisateurTemporaire = new Utilisateur(Long.getLong("1"),"Tommy",LocalDate.now(),Type.Utilisateur,new ArrayList<>());

        if (true) {
            //passe un message pour tester et un utilisateur pour référence dans la navigation de l'application
            applicationEventPublisher.publishEvent(new ConnectionEvent(this,"BouttonClick dans connection controleur",utilisateurTemporaire));
        }
    }

}
