package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_produits;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitsService {
    private Repo_produits produitRepository;

    @Autowired
    public void setProduitRepository(Repo_produits produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Transactional
    public void saveProduit(Produit produit){
        this.produitRepository.save(produit);
    }
}
