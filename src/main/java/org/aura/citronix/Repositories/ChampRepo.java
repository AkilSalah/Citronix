package org.aura.citronix.Repositories;

import org.aura.citronix.Entities.Champ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampRepo extends JpaRepository<Champ, Integer> {

}
