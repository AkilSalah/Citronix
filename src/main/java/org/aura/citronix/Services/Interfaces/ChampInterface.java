package org.aura.citronix.Services.Interfaces;

import org.aura.citronix.DTO.Request.ChampRequest;
import org.aura.citronix.DTO.Response.ChampResponse;

import java.util.List;

public interface ChampInterface {
    ChampResponse getChampById(int id);
    List<ChampResponse> getAllChamps();
    ChampResponse addChamp(ChampRequest champRequest);
    ChampResponse updateChamp(ChampRequest champRequest, int id);
    void deleteChamp(int id);
}
