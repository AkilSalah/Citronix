package org.aura.citronix.Services.Implementation;

import lombok.RequiredArgsConstructor;
import org.aura.citronix.DTO.Response.DetailRecolteResponse;
import org.aura.citronix.Entities.DetailRecolte;
import org.aura.citronix.Mapper.RecolteDetailMapper;
import org.aura.citronix.Repositories.RecolteDetailRepo;
import org.aura.citronix.Services.Interfaces.DetailRecolteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class DetailRecolteImpl implements DetailRecolteInterface {

    private final RecolteDetailRepo DetailRepo;
    private final RecolteDetailMapper DetailMapper;

    @Override
    public DetailRecolteResponse getDetailRecolte(int id) {
        DetailRecolte detailRecolte = DetailRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No such detail recolte"));
    }

    @Override
    public List<DetailRecolteResponse> getAllDetailRecolte() {
        return List.of();
    }

    @Override
    public DetailRecolteResponse addDetailRecolte(DetailRecolteResponse detailRecolte) {
        return null;
    }
}
