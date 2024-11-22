package org.aura.citronix.Services.Implementation;

import lombok.RequiredArgsConstructor;
import org.aura.citronix.DTO.Request.RecolteRequest;
import org.aura.citronix.DTO.Response.RecolteResponse;
import org.aura.citronix.Entities.Arbre;
import org.aura.citronix.Entities.Champ;
import org.aura.citronix.Entities.DetailRecolte;
import org.aura.citronix.Entities.Recolte;
import org.aura.citronix.Exceptions.ChampException;
import org.aura.citronix.Exceptions.RecolteException;
import org.aura.citronix.Mapper.RecolteMapper;
import org.aura.citronix.Repositories.ArbreRepo;
import org.aura.citronix.Repositories.ChampRepo;
import org.aura.citronix.Repositories.RecolteRepo;
import org.aura.citronix.Services.Interfaces.RecolteInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class RecolteImpl implements RecolteInterface {

    private final RecolteRepo recolteRepo;
    private final RecolteMapper recolteMapper;
    private final ChampRepo champRepo;
    private final ArbreRepo arbreRepo;

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
        // Vérifier si le champ existe
        Champ existingChamp = champRepo.findById(recolteRequest.champId())
                .orElseThrow(() -> new ChampException(recolteRequest.champId()));

        // Vérifier si une récolte existe déjà pour ce champ et cette saison
        boolean recolteExist = recolteRepo.existingRecolteByChampAndSaison(recolteRequest.champId(), recolteRequest.saison());
        if (recolteExist) {
            throw new IllegalArgumentException("Ce champ a déjà une récolte pour la saison " + recolteRequest.saison());
        }

        // Mapper la récolte à partir de la requête
        Recolte recolte = recolteMapper.toEntity(recolteRequest);
        recolte.setChamp(existingChamp);

        // Initialiser la liste des détails de récolte
        double totalQuantite = 0.0;

        // Récupérer tous les arbres associés au champ
        List<Arbre> arbres = arbreRepo.findAllByChampId(existingChamp.getId());
        List<DetailRecolte> detailsRecolte = new ArrayList<>();

        for (Arbre arbre : arbres) {
            // Calculer la quantité pour chaque arbre
            double quantite = arbre.getAge() < 3 ? 2.5 :
                    arbre.getAge() <= 10 ? 12.0 :
                            arbre.getAge() <= 20 ? 20.0 : 0.0;

            // Ajouter le détail de la récolte
            DetailRecolte detailRecolte = DetailRecolte.builder()
                    .recolte(recolte)
                    .arbre(arbre)
                    .quantite(quantite) // Mettre la quantité calculée
                    .build();
            detailsRecolte.add(detailRecolte);

            // Ajouter la quantité au total
            totalQuantite += quantite;
        }

        // Ajouter les détails à la récolte
        recolte.setDetails(detailsRecolte);
        recolte.setQuantiteTotale(totalQuantite);

        // Sauvegarder la récolte et les détails
        recolteRepo.save(recolte);

        // Retourner la réponse
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
        existingRecolte.setQuantiteTotale(recolteRequest.quantiteTotale());
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
