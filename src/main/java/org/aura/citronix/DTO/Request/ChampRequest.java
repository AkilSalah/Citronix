package org.aura.citronix.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.aura.citronix.DTO.Response.FermeDto;
import org.aura.citronix.Entities.Ferme;


public record ChampRequest(
        @Positive(message = "La surface du champ doit être positive")
        double champSurface,

        @NotBlank(message = "Le nom du champ est obligatoire")
        @Size(max = 50)
        String champName

//        @Positive(message = "L'ID de la ferme doit être positif")
//        int ferme_id
) {}

