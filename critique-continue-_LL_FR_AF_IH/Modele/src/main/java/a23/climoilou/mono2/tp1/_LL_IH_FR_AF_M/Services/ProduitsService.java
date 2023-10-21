package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Produit;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Utilisateur;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_produits;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Transactional
    public void deleteProduit(Produit produit){
        this.produitRepository.delete(produit);
    }

    public Repo_produits getProduitRepository() {
        return produitRepository;
    }

    public Produit creationValidationProduit(String nom, String description, LocalDate dateSortie, String image){
        Produit produitRet = null;

        if(nom!=""&&description!=""&&dateSortie!=null){
            produitRet = Produit.builder().nom(nom).dateDeSortie(dateSortie).description(description).image(image).build();
        }

        return produitRet;
    }

    @Transactional
    public List<Produit> retourLesProduits(){
        List<Produit> produitList = new ArrayList<>();

        for (Produit produit: this.produitRepository.findAll()) {
            produitList.add(produit);
        }
        return produitList;
    }
}
