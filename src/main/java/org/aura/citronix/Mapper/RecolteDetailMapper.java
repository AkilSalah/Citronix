package org.aura.citronix.Mapper;


import org.aura.citronix.DTO.Request.RecolteRequest;
import org.aura.citronix.DTO.Response.DetailRecolteResponse;
import org.aura.citronix.Entities.DetailRecolte;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecolteDetailMapper {

    @Mapping(target ="arbreId",source = "arbre.id" )
    @Mapping(target = "dateDePlantation",source = "arbre.dateDePlantation")
    @Mapping(target = "age" ,source = "arbre.age")
    @Mapping(target = "productiviteAnnuelle",source = "arbre.productiviteAnnuelle")
    @Mapping(target = "recolteId",source = "recolte.id")
    @Mapping(target = "dateDeRecolte",source = "recolte.dateDeRecolte")
    @Mapping(target = "saison",source = "recolte.saison")
    DetailRecolteResponse toResponse(DetailRecolte detailRecolte);
}
