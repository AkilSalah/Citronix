package org.aura.citronix.Services.Interfaces;

import org.aura.citronix.DTO.FermeDto;
import org.aura.citronix.Entities.Ferme;

import java.util.List;

public interface FermeInterface {
    List<FermeDto> getFermeList();
    FermeDto getFermeById(int id);
    Ferme addFerme(Ferme ferme);
    Ferme updateFerme(Ferme ferme, int id);
    void deleteFerme(int id);

}
