package org.aura.citronix.Entities;

import jakarta.persistence.*;
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

    @Column(name = "date_de_plantation")
    private LocalDate dateDePlantation;

    private int age;
    private double productiviteAnnuelle;

    @ManyToMany(mappedBy = "arbres")
    private List<Recolte> recoltes = new ArrayList<>();
}

