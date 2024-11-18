package org.aura.citronix.Mapper;

import org.aura.citronix.DTO.Request.ChampRequest;
import org.aura.citronix.DTO.Response.ChampDto;
import org.aura.citronix.Entities.Champ;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChampMapper {
    Champ requestToEntity(ChampRequest champRequest);
    ChampDto toDto(Champ champ);
    List<ChampDto> toDtoList(List<Champ> champs);
}
