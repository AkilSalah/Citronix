package org.aura.citronix.Services.Interfaces;

import org.aura.citronix.DTO.Request.FermeRequest;
import org.aura.citronix.DTO.Response.FermeDto;
import org.aura.citronix.Entities.Ferme;

import java.util.List;

public interface FermeInterface {
    List<FermeDto> getFermeList();
    FermeDto getFermeById(int id);
    FermeDto addFerme(FermeRequest fermeRequest);
    FermeDto updateFerme(FermeRequest fermeRequest, int id);
    void deleteFerme(int id);

}
