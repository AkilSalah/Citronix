package org.aura.citronix.Mapper;


import org.aura.citronix.DTO.Request.ArbreRequest;
import org.aura.citronix.DTO.Response.ArbreResponse;
import org.aura.citronix.DTO.Response.ChampResponse;
import org.aura.citronix.DTO.Response.FermeResponse;
import org.aura.citronix.Entities.Arbre;
import org.aura.citronix.Entities.Champ;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ArbreMapper {
    @Mapping(target = "age", expression = "java(calculateAge(arbre.getDateDePlantation()))")
    ArbreResponse toResponse(Arbre arbre);

    default int calculateAge(LocalDate dateDePlantation) {
        if (dateDePlantation == null) {
            return 0; // or handle as you prefer
        }
        return Period.between(dateDePlantation, LocalDate.now()).getYears();
    }

    Arbre requestToEntity(ArbreRequest arbreRequest);

    List<ArbreResponse> toResponseList(List<Arbre> arbreList);
}