package org.aura.citronix.DTO.Response;

import lombok.*;
import org.aura.citronix.Entities.Enum.Saison;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailRecolteResponse {
    private int id;
    private int arbreId;
    private LocalDate dateDePlantation;
    private double productiviteAnnuelle;
    private int age;
    private int recolteId;
    private LocalDate dateDeRecolte;
    private Saison saison;
    private double quantite;
}
