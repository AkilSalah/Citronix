package org.aura.citronix.DTO.Response;

import lombok.*;
import org.aura.citronix.Entities.Enum.Saison;

import java.time.LocalDate;

@Builder
public record DetailRecolteResponse (
        int id,
        int arbreId,
        LocalDate dateDePlantation,
        double productiviteAnnuelle,
        int age,
        int recolteId,
        LocalDate dateDeRecolte,
        Saison saison,
        double quantite
)
{}
