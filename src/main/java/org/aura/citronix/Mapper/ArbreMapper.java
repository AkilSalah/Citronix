package org.aura.citronix.Mapper;


import org.aura.citronix.DTO.Request.ArbreRequest;
import org.aura.citronix.DTO.Response.ArbreMinimalResponse;
import org.aura.citronix.DTO.Response.ArbreResponse;
import org.aura.citronix.Entities.Arbre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Mapper(componentModel = "spring" )
public interface ArbreMapper {

    @Mapping(target = "age", source = "age")
    ArbreMinimalResponse toMinimalResponse(Arbre arbre);

    @Mapping(target = "age", source = "age")
    ArbreResponse toResponse(Arbre arbre);

    @Mapping(target = "champ.id", source = "champId")
    Arbre requestToEntity(ArbreRequest arbreRequest);

    List<ArbreResponse> toResponseList(List<Arbre> arbreList);
}