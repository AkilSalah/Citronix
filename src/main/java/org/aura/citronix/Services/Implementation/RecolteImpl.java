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
import org.aura.citronix.Services.Interfaces.ChampInterface;
import org.aura.citronix.Services.Interfaces.RecolteInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
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
        Recolte recolte = recolteMapper.toEntity(recolteRequest);
        recolte.setChamp(existingChamp);
        recolteRepo.save(recolte);
        return recolteMapper.toResponse(recolte);
    }

    @Override
    public RecolteResponse updateRecolte(int id, RecolteRequest recolteRequest) {
        return null;
    }

    @Override
    public RecolteResponse deleteRecolte(int id) {
        return null;
    }
}
