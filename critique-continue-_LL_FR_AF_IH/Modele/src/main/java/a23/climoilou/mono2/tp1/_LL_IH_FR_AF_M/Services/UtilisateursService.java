package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_Utilisateur;
import jakarta.transaction.Transactional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateursService {
  private Repo_Utilisateur utilisateurRepo;

  @Autowired
    public void setUtilisateurRepo(Repo_Utilisateur utilisateurRepo) {
        this.utilisateurRepo = utilisateurRepo;
    }

    public Utilisateur validationCreationService(DatePicker dateNaissance, Type type, TextField nomUtilisateur,TextField identifiant){
        //validation et creation d'utilisateur
        Utilisateur utilisateur = null;
        if(nomUtilisateur.getText()!="" && dateNaissance !=null && type!=null && identifiant.getText()!="") {
            utilisateur = getUtilisateurRepo().findFirstByNom(identifiant.getText());

            if (utilisateur == null) {
                utilisateur = Utilisateur.builder().nom(nomUtilisateur.getText()).type(type).dateDeNaissance(dateNaissance.getValue()).identifiant(identifiant.getText()).build();
                //applicationEventPublisher.publishEvent(new CreationCompteEvent(this,new Utilisateur()));
            }
        }
        return utilisateur;
    }
    @Transactional
    public void sauvegarderUtilisateur(Utilisateur utilisateur){
        this.utilisateurRepo.save(utilisateur);
    }

    @Transactional
    public void surpprimerUtilisateur(Utilisateur utilisateur){
        this.utilisateurRepo.delete(utilisateur);
    }

    @Transactional
    public List<Utilisateur> retourLesUtilisateurs(){
      List<Utilisateur> utilisateursList = new ArrayList<>();

        for (Utilisateur util: this.utilisateurRepo.findAll()) {
            utilisateursList.add(util);
        }
        return utilisateursList;
    }

  public Repo_Utilisateur getUtilisateurRepo() {
    return utilisateurRepo;
  }
}
