package org.aura.citronix.DTO.Response;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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