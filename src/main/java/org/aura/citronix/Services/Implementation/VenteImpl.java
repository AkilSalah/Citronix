package org.aura.citronix.Services.Implementation;


import lombok.RequiredArgsConstructor;
import org.aura.citronix.DTO.Request.VenteRequest;
import org.aura.citronix.DTO.Response.VenteResponse;
import org.aura.citronix.Entities.Recolte;
import org.aura.citronix.Entities.Vente;
import org.aura.citronix.Exceptions.RecolteException;
import org.aura.citronix.Exceptions.VenteException;
import org.aura.citronix.Mapper.VenteMapper;
import org.aura.citronix.Repositories.RecolteRepo;
import org.aura.citronix.Repositories.VenteRepo;
import org.aura.citronix.Services.Interfaces.VenteInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VenteImpl implements VenteInterface {

    private final VenteRepo venteRepo;
    private final VenteMapper venteMapper;
    private final RecolteRepo recolteRepo;

    @Override
    public List<VenteResponse> getAllVentes() {
        List<Vente> ventes = venteRepo.findAll();
        return ventes.stream().map(venteMapper::toVenteResponse).toList() ;
    }

    @Override
    public VenteResponse getVenteById(int id) {
        Vente vente = venteRepo.findById(id)
                .orElseThrow(() -> new VenteException(id));
        return venteMapper.toVenteResponse(vente);
    }

    @Override
    public VenteResponse addVente(VenteRequest venteRequest) {
        Recolte recolte = recolteRepo.findById(venteRequest.recolteId())
                .orElseThrow(() -> new RecolteException(venteRequest.recolteId()));

        if (recolte.getQuantiteTotale() == 0) {
            throw new IllegalArgumentException("La quantité de cette récolte est épuisée.");
        }
        if (venteRequest.quantiteVendue() > recolte.getQuantiteTotale()) {
            throw new IllegalArgumentException("La quantité demandée dépasse la quantité disponible pour cette récolte.");
        }

        Vente vente = venteMapper.toEntity(venteRequest);
        vente.setRevenu(venteRequest.quantiteVendue() * venteRequest.prixUnitaire());
        venteRepo.save(vente);

        recolte.setQuantiteTotale(recolte.getQuantiteTotale() - venteRequest.quantiteVendue());
        recolteRepo.save(recolte);

        return venteMapper.toVenteResponse(vente);
    }


    @Override
    public VenteResponse updateVente(int id, VenteRequest venteRequest) {
        return null;
    }

    @Override
    public void deleteVente(int id) {
        if (!venteRepo.existsById(id)) {
            throw new VenteException(id);
        }
        venteRepo.deleteById(id);
    }
}
