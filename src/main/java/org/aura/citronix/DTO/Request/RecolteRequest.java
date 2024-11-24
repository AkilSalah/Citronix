package org.aura.citronix.DTO.Request;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import org.aura.citronix.Entities.Enum.Saison;

import java.time.LocalDate;
@Builder
public record RecolteRequest (

        @NotNull(message = "La date de récolte est obligatoire.")
        @PastOrPresent(message = "La date de récolte doit être dans le présent ou le passe.")
        @Column(name = "date_de_recolte")
        LocalDate dateDeRecolte,

        @NotNull(message = "La saison est obligatoire.")
        @Enumerated(EnumType.STRING)
        Saison saison,

        @Positive(message = "Id de champ doit etre positive ")
        Integer champId
)
{}
