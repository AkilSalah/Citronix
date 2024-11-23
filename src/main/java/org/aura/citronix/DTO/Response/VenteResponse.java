package org.aura.citronix.DTO.Response;

import lombok.*;

import java.time.LocalDate;

@Builder
public record VenteResponse (
        Integer id,
        LocalDate dateDeVente,
        double prixUnitaire,
        String clientName,
        double revenu,
        double quantiteVendue,
        RecolteMinimaleResponse recolteVente
) {

}
