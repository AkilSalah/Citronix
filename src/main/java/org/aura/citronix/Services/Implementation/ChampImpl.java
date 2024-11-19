//package org.aura.citronix.Services.Implementation;
//
//import org.aura.citronix.DTO.Request.ChampRequest;
//import org.aura.citronix.DTO.Response.ChampDto;
//import org.aura.citronix.Entities.Champ;
//import org.aura.citronix.Entities.Ferme;
//import org.aura.citronix.Exceptions.ChampException;
//import org.aura.citronix.Exceptions.FermeException;
//import org.aura.citronix.Mapper.ChampMapper;
//import org.aura.citronix.Repositories.ChampRepo;
//import org.aura.citronix.Repositories.FermeRepo;
//import org.aura.citronix.Services.Interfaces.ChampInterface;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional
// class ChampImpl implements ChampInterface {
//    private final ChampRepo champRepo;
//    private final ChampMapper champMapper;
//    private final FermeRepo fermeRepo;
//
//    @Autowired
//    public ChampImpl(ChampRepo champRepo, ChampMapper champMapper, FermeRepo fermeRepo) {
//        this.champRepo = champRepo;
//        this.champMapper = champMapper;
//        this.fermeRepo = fermeRepo;
//    }
//
//    @Override
//    public ChampDto getChampById(int id) {
//        Champ champ = champRepo.findById(id)
//                .orElseThrow(() -> new ChampException(id));
//        return champMapper.toDTO(champ);
//    }
//
//    @Override
//    public List<ChampDto> getAllChamps() {
//        return champMapper.toDtoList(champRepo.findAll());
//    }
//
//    @Override
//    public ChampDto addChamp(ChampRequest request) {
//        if (request == null) {
//            throw new IllegalArgumentException("ChampRequest cannot be null");
//        }
//
//        Champ champ = champMapper.toEntity(request);
//        Champ savedChamp = champRepo.save(champ);
//        return champMapper.toDTO(savedChamp);
//    }
//
//
//    @Override
//    public ChampDto updateChamp(ChampRequest request, int id) {
//        Champ champ = champRepo.findById(id)
//                .orElseThrow(() -> new ChampException(id));
//
//        Ferme ferme = fermeRepo.findById(request.fermeId())
//                .orElseThrow(() -> new FermeException(request.fermeId()));
//
//        champ.setChampName(request.champName());
//        champ.setChampSurface(request.champSurface());
//        champ.setFerme(ferme);
//
//        return champMapper.toDTO(champRepo.save(champ));
//    }
//
//    @Override
//    public void deleteChamp(int id) {
//        if (!champRepo.existsById(id)) {
//            throw new ChampException(id);
//        }
//        champRepo.deleteById(id);
//    }
//}
