package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Repo_produits extends CrudRepository<Produit, Long> {

    //trouve le produit selon l'identifiant passé en paramètre
    @Query("select P from Produit P where P.nom = :nom")
    Produit findFirstByNom(@Param("nom") String nom);

    @Query("select P from Produit P order by P.nom asc ")
    List<Produit> retourneProduitParDate();



}
