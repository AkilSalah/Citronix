package org.aura.citronix.DTO.Response;

import lombok.Builder;
import org.aura.citronix.Entities.Enum.Saison;

import java.time.LocalDate;
import java.util.List;
@Builder
public record RecolteResponse(
         Integer id,
         LocalDate dateDeRecolte,
         double quantiteTotale,
         Saison saison,
         List<DetailRecolteResponse>detailsRecolte,
         ChampMinimalResponse champ
){}
