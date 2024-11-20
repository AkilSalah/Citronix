package org.aura.citronix.DTO.Request;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;


public record ArbreRequest (

        @NotNull(message = "La date de plantation est obligatoire.")
        @PastOrPresent(message = "La date de plantation doit être dans le présent ou le passe.")
        @Column(name = "date_de_plantation")
        LocalDate dateDePlantation,

        @Positive(message = "L'ID de la ferme doit être positif")
        Integer champId

){}
