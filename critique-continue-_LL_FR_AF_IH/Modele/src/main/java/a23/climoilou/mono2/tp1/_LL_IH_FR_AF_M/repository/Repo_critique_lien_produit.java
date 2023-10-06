package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.CritiqueLienProduit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface Repo_critique_lien_produit extends CrudRepository<CritiqueLienProduit, Long> {
    //@Query("select cl.produitActuel,count from CritiqueLienProduit cl group by  cl.produitActuel")
}
