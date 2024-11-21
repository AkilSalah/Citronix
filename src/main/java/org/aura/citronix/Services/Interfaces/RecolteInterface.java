package org.aura.citronix.Services.Interfaces;

import org.aura.citronix.DTO.Request.RecolteRequest;
import org.aura.citronix.DTO.Response.RecolteResponse;

import java.util.List;

public interface RecolteInterface {

    List<RecolteResponse> getAllRecoltes();
    RecolteResponse getRecolteById(int id);
    RecolteResponse createRecolte(RecolteRequest recolteRequest);
    RecolteResponse updateRecolte(int id, RecolteRequest recolteRequest);
    void deleteRecolte(int id);
}
