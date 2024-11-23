package org.aura.citronix.DTO.Request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record VenteRequest (
        @NotNull(message = "La date de vente ne peut pas être nulle.")
        @PastOrPresent(message = "La date de vente doit être dans le passe ou le présent.")
        LocalDate dateDeVente,

        @Positive(message = "Le prix unitaire doit être un nombre positif.")
        Double prixUnitaire,

        @NotBlank(message = "Le nom du client ne peut pas être vide.")
        @Size(max = 100, message = "Le nom du client ne doit pas dépasser 100 caractères.")
        String clientName,

        @Positive(message = "La quantité vendue doit être un nombre positif.")
        Double quantiteVendue,

        @Positive(message = "Le Id de recolte doit être un nombre positif.")
        Integer recolteId
) {
}
