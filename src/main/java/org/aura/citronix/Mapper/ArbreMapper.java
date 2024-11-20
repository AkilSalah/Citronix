package org.aura.citronix.Mapper;


import org.aura.citronix.DTO.Request.ArbreRequest;
import org.aura.citronix.DTO.Response.ArbreResponse;
import org.aura.citronix.Entities.Arbre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface ArbreMapper {

    Arbre toEntity(ArbreRequest arbreRequest);

    ArbreResponse toResponse(Arbre arbre);

    List<ArbreResponse> toResponseList(List<Arbre> arbreList);
}
