package org.aura.citronix.DTO;

import lombok.*;
import org.aura.citronix.Entities.Enum.Saison;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecolteDto {
    private Integer id;
    private LocalDate dateDeRecolte;
    private double quantiteTotale;
    private Saison saison;
    private List<DetailRecolteDto> detailsRecolte;
}