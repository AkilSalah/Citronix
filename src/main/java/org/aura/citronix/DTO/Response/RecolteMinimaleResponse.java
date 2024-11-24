package org.aura.citronix.DTO.Response;

import lombok.Builder;
import org.aura.citronix.Entities.Enum.Saison;

import java.time.LocalDate;
@Builder
public record RecolteMinimaleResponse (
        Integer id,
        LocalDate dateDeRecolte,
        double quantiteTotale,
        Saison saison
){}
