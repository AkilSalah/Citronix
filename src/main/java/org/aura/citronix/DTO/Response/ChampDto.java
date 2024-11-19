package org.aura.citronix.DTO.Response;

import java.util.List;



public record ChampDto (
        int id,
        double champSurface,
        String champName
//        FermeDto ferme,
//        List<ArbreDto> arbres
) {}

