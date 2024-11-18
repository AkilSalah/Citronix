package org.aura.citronix.DTO.Request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FermeRequest {
    @NotBlank(message = "Le nom de la ferme ne peut pas être vide")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "La localisation est obligatoire")
    @Size(max = 255)
    private String localisation;

    @Positive(message = "La superficie doit être positive")
    private double superficie;

    @FutureOrPresent
    private LocalDate dateDeCreation;
}
