package org.aura.citronix.DTO.Response;

import lombok.Builder;

import java.util.List;
@Builder
public record ChampResponse(
        int id,
        String champName,
        double champSurface,
        FermeMinimalResponse ferme,
        List<ArbreMinimalResponse> arbres
) { }


