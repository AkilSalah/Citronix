package org.aura.citronix.Mapper;


import org.aura.citronix.DTO.Request.ChampRequest;
import org.aura.citronix.DTO.Response.ChampResponse;
import org.aura.citronix.DTO.Response.FermeResponse;
import org.aura.citronix.Entities.Champ;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChampMapper {

    @Mapping(source = "fermeId",target = "ferme.id")
    Champ toEntity(ChampRequest champRequest);

    @Mapping(target = "ferme" ,ignore = true)
    ChampResponse toDTO(Champ champ);

    List<ChampResponse> toDtoList(List<Champ> champs);
}

