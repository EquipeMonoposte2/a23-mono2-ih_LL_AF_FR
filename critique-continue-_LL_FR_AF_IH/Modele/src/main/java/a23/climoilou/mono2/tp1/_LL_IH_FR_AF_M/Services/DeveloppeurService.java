package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Developpeur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_developpeur;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloppeurService {

    private Repo_developpeur repoDeveloppeur;
    @Transactional
    public List<Developpeur>  getAll(){
       return repoDeveloppeur.findAll();
    }

    @Transactional
    public void updateWithID(String note, Long id){
        repoDeveloppeur.updateById(note,id);
    }

    @Autowired
    public void setRepoDeveloppeur(Repo_developpeur repoDeveloppeur) {
        this.repoDeveloppeur = repoDeveloppeur;
    }
}
