package org.aura.citronix.Services.Interfaces;

import org.aura.citronix.DTO.Request.VenteRequest;
import org.aura.citronix.DTO.Response.VenteResponse;
import org.aura.citronix.Entities.Vente;

import java.util.List;

public interface VenteInterface {
    List<VenteResponse> getAllVentes();
    VenteResponse getVenteById(int id);
    VenteResponse addVente(VenteRequest venteRequest);
    VenteResponse updateVente(int id, VenteRequest venteRequest);
    void deleteVente(int id);
}
