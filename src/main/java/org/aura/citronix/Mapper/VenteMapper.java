package org.aura.citronix.Mapper;

import org.aura.citronix.DTO.Request.VenteRequest;
import org.aura.citronix.DTO.Response.VenteResponse;
import org.aura.citronix.Entities.Vente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VenteMapper {

    VenteResponse toVenteResponse(Vente vente);

    @Mapping(target = "recolte.id" ,source = "recolteId" )
    Vente fromVenteResponse(VenteRequest venteRequest);
}
