package org.aura.citronix.DTO.Request;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.aura.citronix.Entities.Enum.Saison;

import java.time.LocalDate;

public record RecolteRequest (

        @NotNull(message = "La date de récolte est obligatoire.")
        @PastOrPresent(message = "La date de récolte doit être dans le présent ou le passe.")
        @Column(name = "date_de_recolte")
        LocalDate dateDeRecolte,

        double quantiteTotale,

        @NotNull(message = "La saison est obligatoire.")
        Saison saison,

        @Positive(message = "Id de champ doit etre positive ")
        Integer champId

)

        {}
