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

    @NotNull(message = "La date de récolte est obligatoire.")
    @FutureOrPresent(message = "La date de récolte doit être dans le présent ou le futur.")
    @Column(name = "date_de_recolte")
    private LocalDate dateDeRecolte;

    @Positive(message = "La quantité totale doit être un nombre positif.")
    private double quantiteTotale;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "La saison est obligatoire.")
    private Saison saison;

    @OneToMany(mappedBy = "recolte", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailRecolte> details = new ArrayList<>();
}

