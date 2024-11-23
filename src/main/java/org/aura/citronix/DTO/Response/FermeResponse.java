package org.aura.citronix.DTO.Response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Builder
public record FermeResponse (
        int id,
        String name,
        String localisation,
        double superficie,
        LocalDate dateDeCreation,
        List<ChampMinimalResponse> champs
)
{ }