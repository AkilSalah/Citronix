package org.aura.citronix.Repositories;

import org.aura.citronix.Entities.DetailRecolte;
import org.aura.citronix.Entities.Enum.Saison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecolteDetail extends JpaRepository<DetailRecolte, Integer> {

    @Query("select rd from DetailRecolte rd where rd.arbre.id = :arbreId and rd.recolte.saison = :saison")
    Boolean findExistDetailByArbreIdAndSaison(@Param("arbreId") Integer arbreId, @Param("saison") Saison saison);
}
