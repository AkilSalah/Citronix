package org.aura.citronix.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ferme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String localisation;

    private double superficie;

    private LocalDate dateDeCreation;

    @OneToMany(mappedBy = "ferme",cascade = CascadeType.ALL)
    private List<Champ> champs = new ArrayList<>();
}