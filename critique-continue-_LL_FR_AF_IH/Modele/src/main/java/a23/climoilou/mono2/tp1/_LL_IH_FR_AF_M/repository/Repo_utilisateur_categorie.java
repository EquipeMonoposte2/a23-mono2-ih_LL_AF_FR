package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.UtilisateurParType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo_utilisateur_categorie extends CrudRepository<UtilisateurParType, Long> {

    @Query("SELECT u FROM UtilisateurParType u WHERE u.type = :type AND u.parent IS NULL")
    UtilisateurParType RetourneLesParentsNullOuVide(Type type);




}
