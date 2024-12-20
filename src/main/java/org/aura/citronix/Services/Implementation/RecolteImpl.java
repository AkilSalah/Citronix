package org.aura.citronix.Services.Implementation;

import lombok.RequiredArgsConstructor;
import org.aura.citronix.DTO.Request.RecolteRequest;
import org.aura.citronix.DTO.Response.RecolteResponse;
import org.aura.citronix.Entities.Champ;
import org.aura.citronix.Entities.Recolte;
import org.aura.citronix.Exceptions.ChampException;
import org.aura.citronix.Exceptions.RecolteException;
import org.aura.citronix.Mapper.RecolteMapper;
import org.aura.citronix.Repositories.ChampRepo;
import org.aura.citronix.Repositories.RecolteRepo;
import org.aura.citronix.Services.Interfaces.RecolteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Transactional
public class RecolteImpl implements RecolteInterface {

    private final RecolteRepo recolteRepo;
    private final RecolteMapper recolteMapper;
    private final ChampRepo champRepo;

    @Override
    public List<RecolteResponse> getAllRecoltes() {
        List<Recolte> recoltes = recolteRepo.findAll();
        return recolteMapper.toResponsesList(recoltes);
    }

    @Override
    public RecolteResponse getRecolteById(int id) {
        Recolte recolte = recolteRepo.findById(id)
                .orElseThrow(() -> new RecolteException(id));
        return recolteMapper.toResponse(recolte);
    }

    @Override
    public RecolteResponse createRecolte(RecolteRequest recolteRequest) {
        Champ existingChamp = champRepo.findById(recolteRequest.champId())
                .orElseThrow(()-> new ChampException(recolteRequest.champId()));
        boolean recolteExist = recolteRepo.existingRecolteByChampAndSaison(recolteRequest.champId(),recolteRequest.saison());
        if(recolteExist) {
            throw new IllegalArgumentException("Ce champ a déjà une récolte pour la saison " + recolteRequest.saison());
        }
        Recolte recolte = recolteMapper.toEntity(recolteRequest);
        recolte.setChamp(existingChamp);
        recolteRepo.save(recolte);
        return recolteMapper.toResponse(recolte);
    }

    @Override
    public RecolteResponse updateRecolte(int id, RecolteRequest recolteRequest) {
        Recolte existingRecolte = recolteRepo.findById(id).orElseThrow(()-> new RecolteException(id));
        Champ champ = champRepo.findById(recolteRequest.champId()).orElseThrow(()-> new ChampException(recolteRequest.champId()));
        boolean recolteExist = recolteRepo.existingRecolteByChampAndSaison(recolteRequest.champId(),recolteRequest.saison());
        if(recolteExist) {
            throw new IllegalArgumentException("Ce champ a déjà une récolte pour la saison " + recolteRequest.saison());
        }
        existingRecolte.setChamp(champ);
        existingRecolte.setDateDeRecolte(recolteRequest.dateDeRecolte());
        existingRecolte.setSaison(recolteRequest.saison());
        recolteRepo.save(existingRecolte);
        return recolteMapper.toResponse(existingRecolte);
    }

    @Override
    public void deleteRecolte(int id) {
        if(!recolteRepo.existsById(id)){
            throw new RecolteException(id);
        }
        recolteRepo.deleteById(id);
    }
}
