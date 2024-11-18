package org.aura.citronix.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChampDto {
    private int id;
    private double champSurface;
    private int fermeId;
    private List<ArbreDto> arbres;
}
