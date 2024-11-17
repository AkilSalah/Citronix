package org.aura.citronix.Entities;

import ch.qos.logback.core.spi.LifeCycle;
import jakarta.persistence.*;
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

    @Column(name = "date_de_recolte")
    private LocalDate dateDeRecolte;

    private double quantiteTotale;

    @Enumerated(EnumType.STRING)
    private Saison saison;

    @ManyToMany
    @JoinTable(
            name = "detail_recolte",
            joinColumns = @JoinColumn(name = "recolte_id"),
            inverseJoinColumns = @JoinColumn(name = "arbre_id")
    )
    private List<Arbre> arbres = new ArrayList<>();
}

