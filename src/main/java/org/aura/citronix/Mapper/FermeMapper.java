package org.aura.citronix.Mapper;

import org.aura.citronix.DTO.Request.FermeRequest;
import org.aura.citronix.DTO.Response.FermeResponse;
import org.aura.citronix.Entities.Ferme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = ChampMapper.class)
public interface FermeMapper {

    Ferme requestToEntity(FermeRequest request);

    FermeResponse toDTO(Ferme ferme);

    Ferme responseToEntity(FermeResponse response);

    List<FermeResponse> toDTOList(List<Ferme> fermes);
}

