package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface Repo_Utilisateur extends CrudRepository<Utilisateur, Long> {

    //trouve l'utilisateur selon l'identifiant passé en paramètre
    @Query("select U from Utilisateur U where U.identifiant = :identifiant")
    Utilisateur findFirstByIdentifiant(@Param("identifiant") String identifiant);
}
