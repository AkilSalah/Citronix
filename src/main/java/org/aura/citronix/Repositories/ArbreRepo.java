package org.aura.citronix.Repositories;

import org.aura.citronix.Entities.Arbre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArbreRepo extends JpaRepository<Arbre, Integer> {
}
