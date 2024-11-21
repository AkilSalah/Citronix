package org.aura.citronix.DTO.Response;

import java.util.List;

public record ChampResponse(
        int id,
        String champName,
        double champSurface,
        FermeResponse ferme,
        List<ArbreResponse> arbres
) {}


