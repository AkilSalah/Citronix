package org.aura.citronix.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Arbre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "La date de plantation est obligatoire.")
    @FutureOrPresent(message = "La date de plantation doit être dans le présent ou le futur.")
    @Column(name = "date_de_plantation")
    private LocalDate dateDePlantation;

    @Positive(message = "L'âge doit être un nombre positif.")
    private int age;

    @Positive(message = "La productivité annuelle doit être un nombre positif.")
    private double productiviteAnnuelle;

    @OneToMany(mappedBy = "arbre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailRecolte> details = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    private Champ champ;
}

