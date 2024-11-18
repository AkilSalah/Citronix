package org.aura.citronix.DTO.Response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FermeDto {
    private int id;
    private String name;
    private String localisation;
    private double superficie;
    private LocalDate dateDeCreation;
    private List<ChampDto> champs;
}
