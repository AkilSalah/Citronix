package org.aura.citronix.Services.Implementation;

import lombok.RequiredArgsConstructor;
import org.aura.citronix.DTO.Request.ChampRequest;
import org.aura.citronix.DTO.Response.ChampResponse;
import org.aura.citronix.DTO.Response.FermeResponse;
import org.aura.citronix.Entities.Champ;
import org.aura.citronix.Entities.Ferme;
import org.aura.citronix.Exceptions.ChampException;
import org.aura.citronix.Mapper.ChampMapper;
import org.aura.citronix.Mapper.FermeMapper;
import org.aura.citronix.Repositories.ChampRepo;
import org.aura.citronix.Services.Interfaces.ChampInterface;
import org.aura.citronix.Services.Interfaces.FermeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Transactional
 class ChampImpl implements ChampInterface {
    private final ChampRepo champRepo;
    private final ChampMapper champMapper;
    private final FermeInterface fermeservice;
    private final FermeMapper fermeMapper;

    @Override
    public ChampResponse getChampById(int id) {
        Champ champ = champRepo.findById(id)
                .orElseThrow(() -> new ChampException(id));
        return champMapper.toDTO(champ);
    }

    @Override
    public List<ChampResponse> getAllChamps() {
        return champMapper.toDtoList(champRepo.findAll());
    }

    @Override
    public ChampResponse addChamp(ChampRequest champRequest) {
        FermeResponse fermeResponse = fermeservice.getFermeById(champRequest.fermeId());
        Ferme ferme = fermeMapper.responseToEntity(fermeResponse);
        if (ferme.getChamps() == null) {
            ferme.setChamps(new ArrayList<>());
        }
        if (champRequest.champSurface()<1000){
            throw new IllegalArgumentException("La superficie d'un champ doit être au minimum de 1000 m².");
        }
        if (champRequest.champSurface()>ferme.getSuperficie() * 0.5){
            throw new IllegalArgumentException("La superficie d'un champ ne peut dépasser 50% de la superficie totale de la ferme.");
        }

        if (ferme.getChamps().size()>=10){
            throw new IllegalArgumentException("Une ferme ne peut contenir plus de 10 champs.");
        }
        Champ champ = champMapper.toEntity(champRequest);
        champ.setFerme(ferme);
        Champ savedChamp = champRepo.save(champ);
        return champMapper.toDTO(savedChamp);
    }

    @Override
    public ChampResponse updateChamp(ChampRequest request, int id) {
        Champ champ = champRepo.findById(id)
                .orElseThrow(() -> new ChampException(id));
        FermeResponse fermeResponse = fermeservice.getFermeById(request.fermeId());
        Ferme ferme = fermeMapper.responseToEntity(fermeResponse);
        if (request.champSurface() < 0.1) {
            throw new IllegalArgumentException("La superficie d'un champ doit être au minimum de 0.1 hectare.");
        }
        if (request.champSurface() > ferme.getSuperficie() * 0.5) {
            throw new IllegalArgumentException("La superficie d'un champ ne peut dépasser 50% de la superficie totale de la ferme.");
        }
        champ.setChampName(request.champName());
        champ.setChampSurface(request.champSurface());
        champ.setFerme(ferme);
        return champMapper.toDTO(champRepo.save(champ));
    }

    @Override
    public void deleteChamp(int id) {
        if (!champRepo.existsById(id)) {
            throw new ChampException(id);
        }
        champRepo.deleteById(id);
    }
}
