package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Critique;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Repo_critique extends CrudRepository<Critique, Long> {
}
