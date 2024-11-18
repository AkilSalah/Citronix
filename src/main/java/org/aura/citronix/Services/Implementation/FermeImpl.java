package org.aura.citronix.Services.Implementation;

import org.aura.citronix.DTO.Request.FermeRequest;
import org.aura.citronix.DTO.Response.FermeDto;
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
public class FermeImpl implements FermeInterface {
    private FermeMapper fermeMapper;
    private FermeRepo fermeRepo;

    @Autowired
    public FermeImpl(FermeRepo fermeRepo, FermeMapper fermeMapper) {
        this.fermeRepo = fermeRepo;
        this.fermeMapper = fermeMapper;
    }

    @Override
    public List<FermeDto> getFermeList() {
        List<Ferme> fermeList = fermeRepo.findAll();
        return fermeMapper.toDTOList(fermeList) ;
    }

    @Override
    public FermeDto getFermeById(int id) {
        Ferme ferme = fermeRepo.findById(id).orElseThrow(()-> new FermeException(id));
        return fermeMapper.toDTO(ferme);
    }

    @Override
    public FermeDto addFerme(FermeRequest fermeRequest) {
        if (fermeRequest == null) {
            throw new IllegalArgumentException("La ferme ne peut pas être nulle");
        }
        Ferme ferme = fermeMapper.requestToEntity(fermeRequest);
        Ferme fermeSaved = fermeRepo.save(ferme);
        return fermeMapper.toDTO(fermeSaved);
    }

    @Override
    public FermeDto updateFerme(FermeRequest fermeRequest, int id) {
        Ferme existingFerme = fermeRepo.findById(id).orElseThrow(()-> new FermeException(id));
        existingFerme.setName(fermeRequest.getName());
        existingFerme.setLocalisation(fermeRequest.getLocalisation());
        existingFerme.setDateDeCreation(fermeRequest.getDateDeCreation());
        existingFerme.setSuperficie(fermeRequest.getSuperficie());
        Ferme fermeSaved = fermeRepo.save(existingFerme);
        return fermeMapper.toDTO(fermeSaved);
    }

    @Override
    public void deleteFerme(int id) {
        Ferme ferme = fermeRepo.findById(id).orElseThrow(()-> new FermeException(id));
        fermeRepo.delete(ferme);
    }


}
