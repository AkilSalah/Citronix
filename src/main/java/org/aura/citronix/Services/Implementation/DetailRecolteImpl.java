package org.aura.citronix.Services.Implementation;

import lombok.RequiredArgsConstructor;
import org.aura.citronix.DTO.Request.RecolteDetailRequest;
import org.aura.citronix.DTO.Response.DetailRecolteResponse;
import org.aura.citronix.Entities.Arbre;
import org.aura.citronix.Entities.DetailRecolte;
import org.aura.citronix.Entities.Recolte;
import org.aura.citronix.Exceptions.ArbreException;
import org.aura.citronix.Exceptions.DetailRecolteException;
import org.aura.citronix.Exceptions.RecolteException;
import org.aura.citronix.Mapper.RecolteDetailMapper;
import org.aura.citronix.Repositories.ArbreRepo;
import org.aura.citronix.Repositories.RecolteDetailRepo;
import org.aura.citronix.Repositories.RecolteRepo;
import org.aura.citronix.Services.Interfaces.DetailRecolteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class DetailRecolteImpl implements DetailRecolteInterface {

    private final RecolteDetailRepo detailRepo;
    private final RecolteDetailMapper detailMapper;
    private final RecolteRepo recolteRepo;
    private final ArbreRepo arbreRepo;

    @Override
    public DetailRecolteResponse getDetailRecolte(int id) {
        DetailRecolte detailRecolte = detailRepo.findById(id)
                .orElseThrow(() -> new DetailRecolteException(id));
        return detailMapper.toResponse(detailRecolte);
    }

    @Override
    public List<DetailRecolteResponse> getAllDetailRecolteByRecolte(int recolteId) {
        recolteRepo.findById(recolteId).orElseThrow(()-> new RecolteException(recolteId));
        List<DetailRecolte> details  = detailRepo.findByRecolteId(recolteId);
        return  details.stream().map(detailMapper::toResponse).toList();
    }

    @Override
    public DetailRecolteResponse addDetailRecolte(RecolteDetailRequest recolteDetailRequest) {
        System.out.println(recolteDetailRequest);
        Recolte recolte = recolteRepo.findById(recolteDetailRequest.recolteId()).orElseThrow(() -> new RecolteException(recolteDetailRequest.recolteId()));
        Arbre arbre = arbreRepo.findById(recolteDetailRequest.arbreId()).orElseThrow(()-> new ArbreException(recolteDetailRequest.arbreId()));
        if (Boolean.TRUE.equals(detailRepo.findExistDetailByArbreIdAndSaison(arbre.getId(),recolte.getSaison()))){
            throw new IllegalArgumentException("Cet arbre a déjà été récolté dans cette saison !");
        }
        DetailRecolte detailRecolteSaved = DetailRecolte.builder().
                recolte(recolte)
                .arbre(arbre).
                quantite(recolteDetailRequest.quantite()).
                build();
        detailRepo.save(detailRecolteSaved);
        recolte.setQuantiteTotale(recolte.getQuantiteTotale() + recolteDetailRequest.quantite());
        detailRepo.save(detailRecolteSaved);
        return detailMapper.toResponse(detailRecolteSaved);
    }
}
