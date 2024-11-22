package org.aura.citronix.Repositories.CustomRepository;

import org.aura.citronix.DTO.Response.FermeResponse;
import org.aura.citronix.Entities.Ferme;

import java.util.List;

public interface FermeCustom {
    List<Ferme> searchFermes(String name, String localisation, Double superficieMin, Double superficieMax);
}
