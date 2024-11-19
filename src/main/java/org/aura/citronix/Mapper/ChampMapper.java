package org.aura.citronix.Mapper;


import org.aura.citronix.DTO.Request.ChampRequest;
import org.aura.citronix.DTO.Response.ChampResponse;
import org.aura.citronix.Entities.Champ;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring", uses = {FermeMapper.class})
public interface ChampMapper {

    @Mapping(source = "fermeId", target = "ferme.id")
    Champ toEntity(ChampRequest champRequest);

    @Mapping(source = "ferme",target = "ferme")
    ChampResponse toDTO(Champ champ);

    List<ChampResponse> toDtoList(List<Champ> champs);
}


