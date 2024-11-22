package org.aura.citronix.DTO.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RecolteDetailRequest (
        @NotNull(message = "Id de recole ne doit pas etre null")
        Integer recolteId,
        @NotNull(message = "Id de arbre ne doit pas etre null")
        Integer arbreId,
        @Positive(message = "La quantité de l'arbre dois etre positive")
        Double quantite
){}
