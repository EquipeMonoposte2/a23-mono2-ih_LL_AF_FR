package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Categorie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repo_Categorie extends CrudRepository<Categorie, Long> {
    List<Categorie> findByParentIsNull();

    List<Categorie> findByParent(Categorie parent);

    List<Categorie> findAll();

    Categorie findByNom(String nom);
}
