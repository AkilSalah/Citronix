package org.aura.citronix.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record RecolteDetailRequest (
        @NotBlank(message = "Id de recole ne doit pas etre null")
        Integer recolteId,
        @NotBlank(message = "Id de arbre ne doit pas etre null")
        Integer arbreId,
        @Positive(message = "La quantité doit être un nombre positif.")
        double quantite
){}
