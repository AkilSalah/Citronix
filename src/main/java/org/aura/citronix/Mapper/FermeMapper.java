package org.aura.citronix.Mapper;

import org.aura.citronix.DTO.Request.FermeRequest;
import org.aura.citronix.DTO.Response.FermeDto;
import org.aura.citronix.Entities.Ferme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FermeMapper {

    Ferme requestToEntity(FermeRequest request);

    FermeDto toDTO(Ferme ferme);

    List<FermeDto> toDTOList(List<Ferme> fermes);

}
