package org.aura.citronix.DTO.Response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FermeResponse {
    private int id;
    private String name;
    private String localisation;
    private double superficie;
    private LocalDate dateDeCreation;
    private List<ChampResponse> champs;
}