package a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.repository;

import a23.climoilou.mono2.tp1._LL_IH_FR_AF_M.Developpeur;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repo_developpeur extends CrudRepository<Developpeur,Long> {

    @Query("select d from Developpeur d")
    List<Developpeur> findAll();

    @Modifying
    @Query("UPDATE Developpeur d SET d.note = :note WHERE d.id = :id")
    void updateById(@Param("note") String note,@Param("id") Long id);
}
