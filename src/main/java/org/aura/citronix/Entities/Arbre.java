package org.aura.citronix.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Data
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

    @Transient
    private int age;

    private double productiviteAnnuelle;

    @OneToMany(mappedBy = "arbre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailRecolte> details = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    private Champ champ;

    public int getAge() {
        if (dateDePlantation == null) {
            return 0;
        }
        return Period.between(dateDePlantation, LocalDate.now()).getYears();
    }

}

