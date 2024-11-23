package org.aura.citronix.Services.Interfaces;

import org.aura.citronix.DTO.Request.RecolteDetailRequest;
import org.aura.citronix.DTO.Response.DetailRecolteResponse;

import java.util.List;

public interface DetailRecolteInterface {

    DetailRecolteResponse getDetailRecolte(int id);
    List<DetailRecolteResponse> getAllDetailRecolteByRecolte(int id);
    DetailRecolteResponse addDetailRecolte(RecolteDetailRequest recolteDetailRequest);
    DetailRecolteResponse updateDetailRecolte(int id, RecolteDetailRequest recolteDetailRequest);
    void deleteDetailRecolte(int id);
}
