package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.CritiqueLienProduit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface Repo_critique_lien_produit extends CrudRepository<CritiqueLienProduit, Long> {
    @Query("SELECT COUNT(cl) FROM CritiqueLienProduit cl WHERE cl.produitActuel.id = :produitId")
    int countByProduitActuel(@Param("produitId") Long produitId);;
}
