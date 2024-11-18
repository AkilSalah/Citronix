package org.aura.citronix.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Champ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "champ_surface")
    @Positive(message = "La surface du champ doit être un nombre positif.")
    private double champSurface;

    @NotBlank(message = "Le nom du champ ne peut pas être vide.")
    @Size(max = 50, message = "Le nom du champ ne doit pas dépasser 50 caractères.")
    private String champName;

    @ManyToOne
    @JoinColumn(name = "ferme_id", nullable = false)
    private Ferme ferme;

    @OneToMany(mappedBy = "champ", cascade = CascadeType.ALL)
    private List<Arbre> arbres = new ArrayList<>();
}
