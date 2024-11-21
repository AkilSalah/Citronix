package org.aura.citronix.Entities;

import ch.qos.logback.core.spi.LifeCycle;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.aura.citronix.Entities.Enum.Saison;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recolte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate dateDeRecolte;

    private double quantiteTotale;

    @Enumerated(EnumType.STRING)
    private Saison saison;

    @OneToMany(mappedBy = "recolte", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailRecolte> details = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "champ_id",nullable = false)
    private Champ champ;

    @OneToMany(mappedBy = "recolte",cascade = CascadeType.ALL,orphanRemoval = true)
    private  List<Vente> ventes = new ArrayList<>();

}

