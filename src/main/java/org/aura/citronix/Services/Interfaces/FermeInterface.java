package org.aura.citronix.Services.Interfaces;

import org.aura.citronix.DTO.Request.FermeRequest;
import org.aura.citronix.DTO.Response.FermeResponse;
import org.aura.citronix.Entities.Ferme;

import java.util.List;

public interface FermeInterface {
    List<FermeResponse> getFermeList();
    FermeResponse getFermeById(int id);
    FermeResponse addFerme(FermeRequest fermeRequest);
    FermeResponse updateFerme(FermeRequest fermeRequest, int id);
    void deleteFerme(int id);
    List<Ferme> searchFermes(String name, String localisation, Double superficieMin, Double superficieMax) ;
}