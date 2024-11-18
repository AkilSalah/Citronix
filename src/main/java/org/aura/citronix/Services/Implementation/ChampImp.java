package org.aura.citronix.Services.Implementation;

import org.aura.citronix.DTO.Request.ChampRequest;
import org.aura.citronix.DTO.Response.ChampDto;
import org.aura.citronix.Entities.Champ;
import org.aura.citronix.Exceptions.ChampException;
import org.aura.citronix.Mapper.ChampMapper;
import org.aura.citronix.Repositories.ChampRepo;
import org.aura.citronix.Services.Interfaces.ChampInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ChampImp implements ChampInterface {
    private final ChampRepo champRepo;
    private final ChampMapper champMapper;
    @Autowired
    public ChampImp(ChampRepo champRepo, ChampMapper champMapper) {
        this.champRepo = champRepo;
        this.champMapper = champMapper;
    }

    @Override
    public ChampDto getFermeById(int id) {
        Champ champ = champRepo.findById(id)
                .orElseThrow(() -> new ChampException(id));
        return champMapper.toDto(champ);
    }

    @Override
    public List<ChampDto> getAllChamps() {
        List<Champ> champs = champRepo.findAll();
        return champMapper.toDtoList(champs);
    }

    @Override
    public ChampDto addChamp(ChampRequest champRequest) {
        if (champRequest == null) {
            throw new IllegalArgumentException("Le champ ne dois pas etre null");
        }
        Champ champ = champMapper.requestToEntity(champRequest);
        Champ champSaved = champRepo.save(champ);
        return champMapper.toDto(champSaved);
    }

    @Override
    public ChampDto updateChamp(ChampRequest champRequest, int id) {
        Champ existingChamp = champRepo.findById(id).orElseThrow(() -> new ChampException(id));
        existingChamp.setChampName(champRequest.getChampName());
        existingChamp.setChampSurface(champRequest.getChampSurface());
        existingChamp.setFerme(champRequest.getFerme());
        Champ champSaved = champRepo.save(existingChamp);
        return champMapper.toDto(champSaved);
    }

    @Override
    public void deleteChamp(int id) {
        Champ champ = champRepo.findById(id).orElseThrow(() -> new ChampException(id));
        champRepo.delete(champ);
    }
}
