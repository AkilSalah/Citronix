package org.aura.citronix.DTO.Response;

import java.time.LocalDate;

public record ArbreMinimalResponse(
        int id,
        LocalDate dateDePlantation,
        int age,
        double productiviteAnnuelle
) { }
