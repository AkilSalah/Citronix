package org.aura.citronix.Repositories;

import org.aura.citronix.Entities.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenteRepo extends JpaRepository<Vente, Integer> {
}
