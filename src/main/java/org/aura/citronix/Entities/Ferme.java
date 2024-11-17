package org.aura.citronix.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
public class Ferme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Le nom de la ferme ne peut pas être vide.")
    @Size(max = 100, message = "Le nom de la ferme ne doit pas dépasser 100 caractères.")
    private String name;

    @NotBlank(message = "La localisation de la ferme ne peut pas être vide.")
    @Size(max = 255, message = "La localisation ne doit pas dépasser 255 caractères.")
    private String localisation;

    @Positive(message = "La superficie doit être un nombre positif.")
    private double superficie;

    @FutureOrPresent(message = "La date de création doit être dans le présent ou le futur.")
    @Column(name = "date_de_creation")
    private LocalDate dateDeCreation;

    @OneToMany(mappedBy = "ferme", cascade = CascadeType.ALL)
    private List<Champ> champs = new ArrayList<>();
}
