package org.aura.citronix.DTO.Response;


import java.time.LocalDate;
import java.util.List;

public record ArbreResponse (
        int id,
        LocalDate dateDePlantation,
        int age,
        double productiviteAnnuelle,
        ChampResponse champ,
        List<DetailRecolteDto> details
){}
