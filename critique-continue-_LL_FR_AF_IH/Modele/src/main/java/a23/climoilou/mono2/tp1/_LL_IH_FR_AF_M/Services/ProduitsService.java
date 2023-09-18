package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_produits;

import java.util.List;

//@Service
public class ProduitsService {

   /* private final Repo_produits repoProduits;

   // @Autowired
    public ProduitsService(Repo_produits repoProduits) {
        this.repoProduits = repoProduits;
    }

    public List<Produit> getAllProduits(){
        return (List<Produit>) repoProduits.findAll();
    }

    public Produit findProduitByID(Long id){
        return repoProduits.findById(id).orElse(null);
    }

    public void createProduits(Produit produits){
        if (produits != null){
            repoProduits.save(produits);
        }else {
            System.err.println("Produit null on create : "+this);
        }
    }

    public void deleteProduits(Produit produits){
        if (produits != null){
            repoProduits.delete(produits);
        }else {
            System.err.println("Produit null on delete : "+this);
        }
    }*/
}
