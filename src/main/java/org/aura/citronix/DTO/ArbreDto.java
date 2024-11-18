package org.aura.citronix.DTO;

import jakarta.persistence.Transient;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArbreDto {
    private int id;
    private LocalDate dateDePlantation;
    private int age;
    private double productiviteAnnuelle;
    private int champId;
    private List<DetailRecolteDto> details;
}
