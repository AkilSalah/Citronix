package org.aura.citronix.UnitTest;

import org.aura.citronix.DTO.Request.RecolteRequest;
import org.aura.citronix.DTO.Response.RecolteResponse;
import org.aura.citronix.Entities.Champ;
import org.aura.citronix.Entities.Enum.Saison;
import org.aura.citronix.Entities.Recolte;
import org.aura.citronix.Exceptions.ChampException;
import org.aura.citronix.Mapper.RecolteMapper;
import org.aura.citronix.Repositories.ChampRepo;
import org.aura.citronix.Repositories.RecolteRepo;
import org.aura.citronix.Services.Implementation.RecolteImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecolteTest {
    @Mock
    private RecolteRepo recolteRepo;
    @Mock
    private RecolteMapper recolteMapper;
    @Mock
    private ChampRepo champRepo;
    @InjectMocks
    private RecolteImpl recolteService;

    @Test
    void createRecolte_Success() {

        RecolteRequest request = RecolteRequest.builder()
                .dateDeRecolte(LocalDate.now())
                .saison(Saison.HIVER)
                .champId(1)
                .build();

        Champ champ = Champ.builder()
                .id(1)
                .build();

        Recolte recolteToSave = Recolte.builder()
                .dateDeRecolte(request.dateDeRecolte())
                .saison(request.saison())
                .champ(champ)
                .build();

        RecolteResponse expectedResponse = RecolteResponse.builder()
                .id(1)
                .dateDeRecolte(request.dateDeRecolte())
                .saison(request.saison())
                .build();

        when(champRepo.findById(request.champId())).thenReturn(Optional.of(champ));
        when(recolteRepo.existingRecolteByChampAndSaison(request.champId(), request.saison()))
                .thenReturn(false);
        when(recolteMapper.toEntity(request)).thenReturn(recolteToSave);
        when(recolteRepo.save(any(Recolte.class))).thenReturn(recolteToSave);
        when(recolteMapper.toResponse(recolteToSave)).thenReturn(expectedResponse);

        RecolteResponse actualResponse = recolteService.createRecolte(request);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse.id(), actualResponse.id());
        assertEquals(expectedResponse.dateDeRecolte(), actualResponse.dateDeRecolte());
        assertEquals(expectedResponse.saison(), actualResponse.saison());

        verify(champRepo).findById(request.champId());
        verify(recolteRepo).existingRecolteByChampAndSaison(request.champId(), request.saison());
        verify(recolteMapper).toEntity(request);
        verify(recolteRepo).save(any(Recolte.class));
        verify(recolteMapper).toResponse(any(Recolte.class));
    }

    @Test
    void createRecolte_WhenChampNotFound_ThrowsChampException() {
        RecolteRequest request = RecolteRequest.builder()
                .dateDeRecolte(LocalDate.now())
                .saison(Saison.HIVER)
                .champId(1)
                .build();

        when(champRepo.findById(request.champId())).thenReturn(Optional.empty());

        assertThrows(ChampException.class, () -> recolteService.createRecolte(request));
        verify(champRepo).findById(request.champId());
    }

    @Test
    void createRecolte_WhenRecolteExistsForSaison_ThrowsIllegalArgumentException() {
        RecolteRequest request = RecolteRequest.builder()
                .dateDeRecolte(LocalDate.now())
                .saison(Saison.HIVER)
                .champId(1)
                .build();

        Champ champ = Champ.builder()
                .id(1)
                .build();

        when(champRepo.findById(request.champId())).thenReturn(Optional.of(champ));
        when(recolteRepo.existingRecolteByChampAndSaison(request.champId(), request.saison()))
                .thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> recolteService.createRecolte(request));
        verify(champRepo).findById(request.champId());
        verify(recolteRepo).existingRecolteByChampAndSaison(request.champId(), request.saison());
    }
}