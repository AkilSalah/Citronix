package org.aura.citronix.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailRecolte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "recolte_id", nullable = false)
    private Recolte recolte;

    @ManyToOne
    @JoinColumn(name = "arbre_id", nullable = false)
    private Arbre arbre;

    @Transient
    private double quantite;

    public double getQuantite() {
        if (arbre.getAge() < 3) {
           return quantite = 2.5;
        } else if (arbre.getAge() <= 10) {
            return quantite = 12.0;
        } else if (arbre.getAge() <= 20) {
           return quantite = 20.0;
        }
        return 0;
    }
}

