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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
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
        vente.setRecolte(recolte);
        venteRepo.save(vente);

        recolte.setQuantiteTotale(recolte.getQuantiteTotale() - venteRequest.quantiteVendue());
        recolteRepo.save(recolte);

        return venteMapper.toVenteResponse(vente);
    }

    @Override
    public VenteResponse updateVente(int id, VenteRequest venteRequest) {
        Vente vente = venteRepo.findById(id)
                .orElseThrow(() -> new VenteException(id));
        Recolte recolte = recolteRepo.findById(venteRequest.recolteId())
                .orElseThrow(() -> new RecolteException(venteRequest.recolteId()));

        double ancienneQuantiteVendue = vente.getQuantiteVendue();
        double nouvelleQuantiteVendue = venteRequest.quantiteVendue();

        if (nouvelleQuantiteVendue < ancienneQuantiteVendue) {
            double quantiteARetourner = ancienneQuantiteVendue - nouvelleQuantiteVendue;
            recolte.setQuantiteTotale(recolte.getQuantiteTotale() + quantiteARetourner);
        } else {
            double quantiteSup = nouvelleQuantiteVendue - ancienneQuantiteVendue;
            if (quantiteSup > recolte.getQuantiteTotale()) {
                throw new IllegalArgumentException("Stock insuffisant");
            }
            recolte.setQuantiteTotale(recolte.getQuantiteTotale() - quantiteSup);
        }

        vente.setQuantiteVendue(nouvelleQuantiteVendue);
        vente.setPrixUnitaire(venteRequest.prixUnitaire());
        vente.setRevenu(nouvelleQuantiteVendue * venteRequest.prixUnitaire());
        vente.setClientName(venteRequest.clientName());
        recolteRepo.save(recolte);
        venteRepo.save(vente);
        return venteMapper.toVenteResponse(vente);
    }
    @Override
    public void deleteVente(int id) {
        if (!venteRepo.existsById(id)) {
            throw new VenteException(id);
        }
        venteRepo.deleteById(id);
    }
}
