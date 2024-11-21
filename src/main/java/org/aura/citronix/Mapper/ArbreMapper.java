package org.aura.citronix.Mapper;


import org.aura.citronix.DTO.Request.ArbreRequest;
import org.aura.citronix.DTO.Response.ArbreResponse;
import org.aura.citronix.Entities.Arbre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Mapper(componentModel = "spring" , uses = {ChampMapper.class})
public interface ArbreMapper {

    @Mapping(target = "age", expression = "java(calculateAge(arbre.getDateDePlantation()))")
    ArbreResponse toResponse(Arbre arbre);

    default int calculateAge(LocalDate dateDePlantation) {
        if (dateDePlantation == null) {
            return 0;
        }
        return Period.between(dateDePlantation, LocalDate.now()).getYears();
    }

    @Mapping(target = "champ.id", source = "champId")
    Arbre requestToEntity(ArbreRequest arbreRequest);

    List<ArbreResponse> toResponseList(List<Arbre> arbreList);
}