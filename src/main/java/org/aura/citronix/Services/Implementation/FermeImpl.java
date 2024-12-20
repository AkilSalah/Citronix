package org.aura.citronix.Services.Implementation;

import lombok.RequiredArgsConstructor;
import org.aura.citronix.DTO.Request.FermeRequest;
import org.aura.citronix.DTO.Response.FermeResponse;
import org.aura.citronix.Entities.Ferme;
import org.aura.citronix.Exceptions.FermeException;
import org.aura.citronix.Mapper.FermeMapper;
import org.aura.citronix.Repositories.FermeRepo;
import org.aura.citronix.Services.Interfaces.FermeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class FermeImpl implements FermeInterface {
    private final FermeMapper fermeMapper;
    private final FermeRepo fermeRepo;

    @Override
    public List<FermeResponse> getFermeList() {
        List<Ferme> fermeList = fermeRepo.findAll();
        return fermeMapper.toDTOList(fermeList) ;
    }

    @Override
    public FermeResponse getFermeById(int id) {
        Ferme ferme = fermeRepo.findById(id).orElseThrow(()-> new FermeException(id));
        return fermeMapper.toDTO(ferme);
    }

    @Override
    public FermeResponse addFerme(FermeRequest fermeRequest) {
        if (fermeRequest == null) {
            throw new IllegalArgumentException("La ferme ne peut pas être nulle");
        }
        Ferme ferme = fermeMapper.requestToEntity(fermeRequest);
        Ferme fermeSaved = fermeRepo.save(ferme);
        return fermeMapper.toDTO(fermeSaved);
    }

    @Override
    public FermeResponse updateFerme(FermeRequest fermeRequest, int id) {
        Ferme existingFerme = fermeRepo.findById(id).orElseThrow(()-> new FermeException(id));
        existingFerme.setName(fermeRequest.name());
        existingFerme.setLocalisation(fermeRequest.localisation());
        existingFerme.setDateDeCreation(fermeRequest.dateDeCreation());
        existingFerme.setSuperficie(fermeRequest.superficie());
        Ferme fermeSaved = fermeRepo.save(existingFerme);
        return fermeMapper.toDTO(fermeSaved);
    }

    @Override
    public void deleteFerme(int id) {
        Ferme ferme = fermeRepo.findById(id).orElseThrow(()-> new FermeException(id));
        fermeRepo.delete(ferme);
    }

    @Override
    public List<Ferme> searchFermes(String name, String localisation, Double superficieMin, Double superficieMax) {
        return fermeRepo.searchFermes(name, localisation, superficieMin, superficieMax);
    }

}