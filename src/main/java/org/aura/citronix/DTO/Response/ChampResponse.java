package org.aura.citronix.DTO.Response;

import java.util.List;

public record ChampResponse(
        int id,
        double champSurface,
        String champName,
        FermeResponse ferme,
        List<ArbreResponse> arbres
) {}
