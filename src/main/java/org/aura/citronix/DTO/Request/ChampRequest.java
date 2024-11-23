package org.aura.citronix.DTO.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ChampRequest(
        @Positive(message = "La surface du champ doit être positive")
        @Min(value = 1000, message = "La superficie d'un champ doit être au minimum de 1000 m².")
        Double champSurface,

        @NotBlank(message = "Le nom du champ est obligatoire")
        @Size(max = 50)
        String champName,

        @Positive(message = "L'ID de la ferme doit être positif")
        Integer fermeId
) {}


