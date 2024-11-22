package org.aura.citronix.DTO.Request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FermeRequest {
    @NotBlank(message = "Le nom de la ferme ne peut pas être vide")
    @Size(max = 100, message = "Le nom de la ferme ne doit pas dépasser 100 caractères")
    private String name;

    @NotBlank(message = "La localisation est obligatoire")
    @Size(max = 255, message = "La localisation ne doit pas dépasser 255 caractères")
    private String localisation;

    @NotNull(message = "La superficie est obligatoire")
    @Positive(message = "La superficie doit être positive")
    private Double superficie;

    @NotNull(message = "La date de création est obligatoire")
    @PastOrPresent(message = "La date de création doit être dans le présent ou le passe")
    private LocalDate dateDeCreation;
}
