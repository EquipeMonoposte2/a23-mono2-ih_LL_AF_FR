package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Services;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Categorie;
import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository.Repo_Categorie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategorieService {

    private final Repo_Categorie categorieRepository;

    public CategorieService(Repo_Categorie categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    // Méthode pour récupérer et afficher la structure d'arbre des catégories
    public void displayCategoryTree() {
        List<Categorie> categories = categorieRepository.findByParentIsNull();
        for (Categorie categorie : categories) {
            System.out.println(categorie.getNom());
            displaySubCategories(categorie, 1);
        }
    }

    // Méthode récursive pour afficher les sous-catégories
    private void displaySubCategories(Categorie categorie, int depth) {
        List<Categorie> subCategories = categorieRepository.findByParent(categorie);
        for (Categorie subCategory : subCategories) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                sb.append("\t");
            }
            sb.append("|-- ").append(subCategory.getNom());
            System.out.println(sb.toString());
            displaySubCategories(subCategory, depth + 1);
        }
    }

    public void ajouterCategorie(Categorie categorie){
        categorieRepository.save(categorie);
    }

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }
}
