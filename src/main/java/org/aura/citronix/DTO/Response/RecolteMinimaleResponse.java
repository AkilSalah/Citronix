package org.aura.citronix.DTO.Response;

import org.aura.citronix.Entities.Enum.Saison;

import java.time.LocalDate;

public record RecolteMinimaleResponse (
        Integer id,
        LocalDate dateDeRecolte,
        double quantiteTotale,
        Saison saison
){
}
