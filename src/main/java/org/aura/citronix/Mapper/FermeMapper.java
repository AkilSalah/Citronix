package org.aura.citronix.Mapper;

import org.aura.citronix.DTO.FermeDto;
import org.aura.citronix.Entities.Ferme;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FermeMapper {

    FermeDto toDTO (Ferme ferme);

    Ferme toEntity (FermeDto fermeDto);
}
