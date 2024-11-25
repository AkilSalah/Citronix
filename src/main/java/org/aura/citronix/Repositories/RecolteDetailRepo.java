package org.aura.citronix.Repositories;

import org.aura.citronix.Entities.DetailRecolte;
import org.aura.citronix.Entities.Enum.Saison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecolteDetailRepo extends JpaRepository<DetailRecolte, Integer> {

    @Query("select count(rd) > 0 from DetailRecolte rd where rd.arbre.id = :arbreId and rd.recolte.id = :recolteId and rd.recolte.saison = :saison")
    boolean findExistDetailByArbreIdAndSaison(@Param("arbreId") int arbreId,@Param("recolteId") int recolteId , @Param("saison") Saison saison);

    @Query("select rd from DetailRecolte rd where rd.recolte.id = :recolteId")
    List<DetailRecolte> findByRecolteId(@Param("recolteId") int recolteId);
}
