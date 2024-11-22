package org.aura.citronix.DTO.Response;

import java.time.LocalDate;

public record FermeMinimalResponse (
        int id,
        String name,
        String localisation,
        double superficie,
        LocalDate dateDeCreation
){ }
