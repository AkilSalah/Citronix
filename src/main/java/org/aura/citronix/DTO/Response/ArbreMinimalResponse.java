package org.aura.citronix.DTO.Response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ArbreMinimalResponse(
        int id,
        LocalDate dateDePlantation,
        int age,
        double productiviteAnnuelle
) { }
