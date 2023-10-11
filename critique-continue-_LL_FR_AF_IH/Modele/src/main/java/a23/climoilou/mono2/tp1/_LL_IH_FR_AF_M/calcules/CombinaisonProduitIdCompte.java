package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.calcules;

import org.springframework.data.jpa.repository.Query;


public record CombinaisonProduitIdCompte(Long produitId, int val) implements Comparable<CombinaisonProduitIdCompte> {

    @Override
    public int compareTo(CombinaisonProduitIdCompte o) {
        return Integer.compare(this.val, o.val);
    }
}
