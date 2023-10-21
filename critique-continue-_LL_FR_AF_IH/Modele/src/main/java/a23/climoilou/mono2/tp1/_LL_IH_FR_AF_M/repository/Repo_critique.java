package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Critique;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Repo_critique extends CrudRepository<Critique, Long> {
    List<Critique> findAllByUtilisateur(Utilisateur utilisateur);

    @Query("select C from Critique  C where C.utilisateur = :utilisateurParam")
    List<Critique> getCritiquesByUtilisateur(@Param("utilisateurParam") Utilisateur utilisateurParam);

}
