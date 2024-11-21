package org.aura.citronix.Services.Interfaces;

import org.aura.citronix.DTO.Response.DetailRecolteResponse;

import java.util.List;

public interface DetailRecolteInterface {

    DetailRecolteResponse getDetailRecolte(int id);
    List<DetailRecolteResponse> getAllDetailRecolte();
    DetailRecolteResponse addDetailRecolte(DetailRecolteResponse detailRecolte);
}
