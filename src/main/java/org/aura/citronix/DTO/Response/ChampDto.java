package org.aura.citronix.DTO.Response;

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
    private String champName;
    private int fermeId;
    private List<ArbreDto> arbres;
}
