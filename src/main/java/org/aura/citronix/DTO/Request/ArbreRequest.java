package org.aura.citronix.DTO.Request;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;


public record ArbreRequest (
        @NotNull(message = "La date de plantation est obligatoire.")
        @FutureOrPresent(message = "La date de plantation doit être dans le présent ou le futur.")
        @Column(name = "date_de_plantation")
        LocalDate dateDePlantation,

        @Positive(message = "La productivité annuelle doit être un nombre positif.")
        double productiviteAnnuelle,

        @Positive(message = "L'ID de la ferme doit être positif")
        Integer champId

){}
