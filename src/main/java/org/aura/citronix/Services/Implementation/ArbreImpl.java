package org.aura.citronix.Services.Implementation;

import lombok.RequiredArgsConstructor;
import org.aura.citronix.DTO.Request.ArbreRequest;
import org.aura.citronix.DTO.Response.ArbreResponse;
import org.aura.citronix.DTO.Response.ChampResponse;
import org.aura.citronix.Entities.Arbre;
import org.aura.citronix.Entities.Champ;
import org.aura.citronix.Exceptions.ArbreException;
import org.aura.citronix.Mapper.ArbreMapper;
import org.aura.citronix.Mapper.ChampMapper;
import org.aura.citronix.Repositories.ArbreRepo;
import org.aura.citronix.Services.Interfaces.ArbreInterface;
import org.aura.citronix.Services.Interfaces.ChampInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class ArbreImpl implements ArbreInterface {
    private final ArbreRepo arbreRepo;
    private final ArbreMapper arbreMapper;
    private final ChampInterface champService;
    private final ChampMapper champMapper;

    @Override
    public List<ArbreResponse> getAllArbres() {
        return arbreMapper.toResponseList(arbreRepo.findAll());
    }

    @Override
    public ArbreResponse getArbresById(int id) {
        Arbre arbre = arbreRepo.findById(id)
                .orElseThrow(() -> new ArbreException(id));
         arbre.setChamp(arbre.getChamp());
        return arbreMapper.toResponse(arbre);
    }

    @Override
    public ArbreResponse addArbre(ArbreRequest arbreRequest) {
        ChampResponse champResponse = champService.getChampById(arbreRequest.champId());
        Champ champ = champMapper.responseToEntity(champResponse);

        int mois = arbreRequest.dateDePlantation().getMonthValue();
        if (mois<3 || mois>5) {
            throw new IllegalArgumentException("Les arbres ne peuvent être plantés qu'entre mars et mai.");
        }

        int nbMaxArbre = (int) (champ.getChampSurface() / 100);
        if (champ.getArbres().size() >= nbMaxArbre) {
            throw new IllegalArgumentException("La densité des arbres dépasse la limite de 100 arbres par hectare.");
        }

        Arbre arbre = arbreMapper.requestToEntity(arbreRequest);
        arbre.setChamp(champ);

        int age = arbre.getAge();
        if (age < 3) {
            arbre.setProductiviteAnnuelle(2.5);
        } else if (age <= 10) {
            arbre.setProductiviteAnnuelle(12.0);
        } else if (age <= 20) {
            arbre.setProductiviteAnnuelle(20.0);
        } else {
            throw new IllegalArgumentException("L'arbre ne peut pas être productif au-delà de 20 ans.");
        }

        Arbre saveArbre= arbreRepo.save(arbre);

        return arbreMapper.toResponse(saveArbre);
    }

    @Override
    public ArbreResponse updateArbre(ArbreRequest arbreRequest, int id) {
        Arbre arbre = arbreRepo.findById(id)
                .orElseThrow(() -> new ArbreException(id));

        ChampResponse champResponse = champService.getChampById(arbreRequest.champId());
        Champ champ = champMapper.responseToEntity(champResponse);

        int mois = arbreRequest.dateDePlantation().getMonthValue();
        if (mois < 3 || mois > 5) {
            throw new IllegalArgumentException("Les arbres ne peuvent être plantés qu'entre mars et mai.");
        }

        int age = arbre.getAge();
        if (age < 3) {
            arbre.setProductiviteAnnuelle(2.5);
        } else if (age <= 10) {
            arbre.setProductiviteAnnuelle(12.0);
        } else if (age <= 20) {
            arbre.setProductiviteAnnuelle(20.0);
        } else {
            throw new IllegalArgumentException("L'arbre ne peut pas être productif au-delà de 20 ans.");
        }
        arbre.setDateDePlantation(arbreRequest.dateDePlantation());
        arbre.setChamp(champ);

        arbreRepo.save(arbre);
        return arbreMapper.toResponse(arbre);
    }

    @Override
    public void deleteArbre(int id) {
        if (!arbreRepo.existsById(id)) {
            throw new ArbreException(id);
        }
        arbreRepo.deleteById(id);
    }
}
