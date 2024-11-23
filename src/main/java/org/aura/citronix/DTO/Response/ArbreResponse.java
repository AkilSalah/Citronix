package org.aura.citronix.DTO.Response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aura.citronix.Entities.Champ;

import java.time.LocalDate;
import java.time.LocalDate;


@Builder
public record ArbreResponse(
        int id,
        LocalDate dateDePlantation,
        int age,
        double productiviteAnnuelle,
        ChampR champ
) {
    public record ChampR(
            int id,
            String champName,
            double champSurface
    ) {
    }
}




