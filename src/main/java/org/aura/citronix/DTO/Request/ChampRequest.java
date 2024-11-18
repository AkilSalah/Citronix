package org.aura.citronix.DTO.Request;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.aura.citronix.Entities.Ferme;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ChampRequest {
    @Column(name = "champ_surface")
    @Positive(message = "La surface du champ doit être un nombre positif.")
    private double champSurface;

    @NotBlank(message = "Le nom du champ ne peut pas être vide.")
    @Size(max = 50, message = "Le nom du champ ne doit pas dépasser 50 caractères.")
    private String champName;

    @ManyToOne
    @JoinColumn(name = "ferme_id", nullable = false)
    private Ferme ferme;
}
