package org.aura.citronix.Services.Interfaces;

import org.aura.citronix.DTO.Request.ChampRequest;
import org.aura.citronix.DTO.Response.ChampDto;

import java.util.List;

public interface ChampInterface {
    ChampDto getFermeById(int id);
    List<ChampDto> getAllChamps();
    ChampDto addChamp(ChampRequest champRequest);
    ChampDto updateChamp(ChampRequest champRequest, int id);
    void deleteChamp(int id);
}
