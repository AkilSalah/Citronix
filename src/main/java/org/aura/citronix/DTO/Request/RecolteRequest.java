package org.aura.citronix.DTO.Request;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.aura.citronix.Entities.Enum.Saison;

import java.time.LocalDate;

public record RecolteRequest (

        @NotNull(message = "La date de récolte est obligatoire.")
        @FutureOrPresent(message = "La date de récolte doit être dans le présent ou le futur.")
        @Column(name = "date_de_recolte")
        LocalDate dateDeRecolte,

        @Positive(message = "La quantité totale doit être un nombre positif.")
        double quantiteTotale,

        @NotNull(message = "La saison est obligatoire.")
        Saison saison,

        @NotBlank(message = "Id de champ ne doit pas etre null")
        Integer champId

)

        {}
