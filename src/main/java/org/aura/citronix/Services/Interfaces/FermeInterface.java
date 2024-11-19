package org.aura.citronix.Services.Interfaces;

import org.aura.citronix.DTO.Request.FermeRequest;
import org.aura.citronix.DTO.Response.FermeResponse;

import java.util.List;

public interface FermeInterface {
    List<FermeResponse> getFermeList();
    FermeResponse getFermeById(int id);
    FermeResponse addFerme(FermeRequest fermeRequest);
    FermeResponse updateFerme(FermeRequest fermeRequest, int id);
    void deleteFerme(int id);

}