package org.aura.citronix.Repositories;

import org.aura.citronix.Entities.Enum.Saison;
import org.aura.citronix.Entities.Recolte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecolteRepo extends JpaRepository<Recolte,Integer> {
@Query("select count (r) > 0 from Recolte r where r.champ.id = :champId and r.saison = :saison")
    Boolean existingRecolteByChampAndSaison(@Param("champId") Integer champId,@Param("saison") Saison saison);
}
