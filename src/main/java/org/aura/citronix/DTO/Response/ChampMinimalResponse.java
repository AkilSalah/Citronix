package org.aura.citronix.DTO.Response;

import java.util.List;

public record ChampMinimalResponse(
        int id,
        String champName,
        double champSurface,
        List<ArbreMinimalResponse> arbres
) {}

