package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Critique;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_critique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class CritiquesService {

    /*private final Repo_critique repoCritique;

    @Autowired
    public CritiquesService(Repo_critique repoCritique) {
        this.repoCritique = repoCritique;
    }


    public List<Critique> getAllCritiques(){
        return (List<Critique>) repoCritique.findAll();
    }

    public Critique getCritiqueByID(Long id){
        return repoCritique.findById(id).orElse(null);
    }

    public void createCritique(Critique critique){
        if (critique!=null){
            repoCritique.save(critique);
        }else {
            System.err.println("Critique null on create : "+this);
        }
    }

    public void deleteCritique(Critique critique){
        if (critique!=null){
            repoCritique.delete(critique);
        }else {
            System.err.println("Critique null on delete : "+this);
        }
    }*/
}
