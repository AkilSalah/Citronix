package org.aura.citronix.Services.Interfaces;

import org.aura.citronix.DTO.Request.ArbreRequest;
import org.aura.citronix.DTO.Response.ArbreResponse;
import org.aura.citronix.Entities.Arbre;

import java.util.List;

public interface ArbreInterface {
    List<ArbreResponse> getAllArbres();
    ArbreResponse getArbresById(int id);
    ArbreResponse addArbre(ArbreRequest arbreRequest);
    ArbreResponse updateArbre(ArbreRequest arbreRequest, int id);
    void deleteArbre(int id);
}
