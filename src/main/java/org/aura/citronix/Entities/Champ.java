package org.aura.citronix.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Champ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String champName;

    private double champSurface;

    @ManyToOne(fetch = FetchType.LAZY)  // Changez à LAZY
    @JoinColumn(name = "ferme_id", nullable = false)
    @JsonBackReference  // Remplacez @JsonIgnore par @JsonBackReference
    private Ferme ferme;

    @OneToMany(mappedBy = "champ", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Arbre> arbres = new ArrayList<>();
}

