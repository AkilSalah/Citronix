package org.aura.citronix.Repositories;

import org.aura.citronix.Entities.Ferme;
import org.aura.citronix.Repositories.CustomRepository.FermeCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FermeRepo extends JpaRepository<Ferme,Integer>, FermeCustom {
}
