package org.aura.citronix.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VenteDto {
    private Integer id;
    private LocalDate dateDeVente;
    private double prixUnitaire;
    private String clientName;
    private double revenu;
    private double quantiteVendue;
    private int recolteId;
}
