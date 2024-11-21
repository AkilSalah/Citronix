package org.aura.citronix.Mapper;


import org.aura.citronix.DTO.Request.RecolteRequest;
import org.aura.citronix.DTO.Response.RecolteResponse;
import org.aura.citronix.Entities.Recolte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecolteMapper {

    @Mapping(target = "detailsRecolte" , source = "details")
    RecolteResponse toResponse(Recolte recolte);

    @Mapping(target = "champ.id",source = "champId")
    Recolte toEntity(RecolteRequest recolteRequest);

    List<RecolteResponse> toResponsesList(List<Recolte> recoltes);
}
