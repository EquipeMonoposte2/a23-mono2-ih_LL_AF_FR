package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Critique;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Type;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
public interface Repo_Utilisateur extends CrudRepository<Utilisateur, Long> {

    //trouve l'utilisateur selon l'identifiant passé en paramètre
    @Query("select U from Utilisateur U where U.identifiant = :identifiant")
    Utilisateur findFirstByIdentifiant(@Param("identifiant") String identifiant);

    @Transactional
    @Modifying
    @Query("update Utilisateur U set U.nom = :nom, U.dateDeNaissance = :dateNaissance, U.type = :type where U.identifiant =:identifiant")
    void updateFirstByIdentifiant(@Param("nom") String nom, @Param("dateNaissance") LocalDate dateNaissance, @Param("type") Type type, @Param("identifiant") String identifiant);

}
