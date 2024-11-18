package org.aura.citronix.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La date de vente ne peut pas être nulle.")
    @FutureOrPresent(message = "La date de vente doit être dans le future ou le présent.")
    @Column(name = "date_de_vente")
    private LocalDate dateDeVente;

    @Positive(message = "Le prix unitaire doit être un nombre positif.")
    private double prixUnitaire;

    @NotBlank(message = "Le nom du client ne peut pas être vide.")
    @Size(max = 100, message = "Le nom du client ne doit pas dépasser 100 caractères.")
    private String clientName;

    @PositiveOrZero(message = "Le revenu doit être positif ou nul.")
    private double revenu;

    @Positive(message = "La quantité vendue doit être un nombre positif.")
    private double quantiteVendue;

    @ManyToOne
    @JoinColumn(name = "recolte_id", nullable = false)
    private Recolte recolte;
}
