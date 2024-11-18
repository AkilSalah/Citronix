package org.aura.citronix.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailRecolteDto {
    private int id;
    private int arbreId;
    private int recolteId;
    private double quantite;
}
