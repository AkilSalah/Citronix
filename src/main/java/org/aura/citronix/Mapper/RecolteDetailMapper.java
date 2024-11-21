package org.aura.citronix.Mapper;


import org.aura.citronix.DTO.Request.RecolteRequest;
import org.aura.citronix.DTO.Response.DetailRecolteResponse;
import org.aura.citronix.Entities.DetailRecolte;
import org.aura.citronix.Entities.Recolte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecolteDetailMapper {

    @Mapping(target ="arbreId",source = "arbre.id" )
    DetailRecolteResponse toResponse(DetailRecolte detailRecolte);

    DetailRecolte toEntity(RecolteRequest recolteRequest);

}
