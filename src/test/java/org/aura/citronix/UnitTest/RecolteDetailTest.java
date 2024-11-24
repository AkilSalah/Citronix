package org.aura.citronix.UnitTest;

import org.aura.citronix.DTO.Request.RecolteDetailRequest;
import org.aura.citronix.DTO.Response.DetailRecolteResponse;
import org.aura.citronix.Entities.Arbre;
import org.aura.citronix.Entities.DetailRecolte;
import org.aura.citronix.Entities.Enum.Saison;
import org.aura.citronix.Entities.Recolte;
import org.aura.citronix.Exceptions.RecolteException;
import org.aura.citronix.Mapper.RecolteDetailMapper;
import org.aura.citronix.Repositories.ArbreRepo;
import org.aura.citronix.Repositories.RecolteDetailRepo;
import org.aura.citronix.Repositories.RecolteRepo;
import org.aura.citronix.Services.Implementation.DetailRecolteImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecolteDetailTest {
    @Mock
    private RecolteDetailRepo detailRepo;
    @Mock
    private RecolteDetailMapper detailMapper;
    @Mock
    private RecolteRepo recolteRepo;
    @Mock
    private ArbreRepo arbreRepo;
    @InjectMocks
    private DetailRecolteImpl detailRecolteService;

    @Test
    void addDetailRecolte_Success() {
        RecolteDetailRequest request = RecolteDetailRequest.builder()
                .recolteId(1)
                .arbreId(1)
                .quantite(10.0)
                .build();

        Recolte recolte = Recolte.builder()
                .id(1)
                .quantiteTotale(0.0)
                .saison(Saison.HIVER)
                .build();

        Arbre arbre = Arbre.builder()
                .id(1)
                .build();

        DetailRecolteResponse expectedResponse = DetailRecolteResponse.builder()
                .id(1)
                .recolteId(1)
                .arbreId(1)
                .quantite(10.0)
                .saison(Saison.HIVER)
                .build();

        when(recolteRepo.findById(anyInt())).thenReturn(Optional.of(recolte));
        when(arbreRepo.findById(anyInt())).thenReturn(Optional.of(arbre));
        when(detailRepo.findExistDetailByArbreIdAndSaison(anyInt(), anyInt(), any(Saison.class)))
                .thenReturn(false);
        when(detailRepo.save(any(DetailRecolte.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        when(detailMapper.toResponse(any(DetailRecolte.class))).thenReturn(expectedResponse);

        DetailRecolteResponse actualResponse = detailRecolteService.addDetailRecolte(request);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse.id(), actualResponse.id());
        assertEquals(expectedResponse.quantite(), actualResponse.quantite());
        assertEquals(expectedResponse.recolteId(), actualResponse.recolteId());
        assertEquals(expectedResponse.arbreId(), actualResponse.arbreId());

        verify(recolteRepo).findById(request.recolteId());
        verify(arbreRepo).findById(request.arbreId());
        verify(detailRepo).findExistDetailByArbreIdAndSaison(
                arbre.getId(),
                recolte.getId(),
                recolte.getSaison()
        );
        verify(detailRepo, times(2)).save(any(DetailRecolte.class));
        verify(detailMapper).toResponse(any(DetailRecolte.class));
    }

    @Test
    void updateDetailRecolte_Success() {
        int detailId = 1;
        RecolteDetailRequest request = RecolteDetailRequest.builder()
                .recolteId(1)
                .arbreId(1)
                .quantite(15.0)
                .build();

        Recolte recolte = Recolte.builder()
                .id(1)
                .quantiteTotale(10.0)
                .build();

        DetailRecolte existingDetail = DetailRecolte.builder()
                .id(detailId)
                .recolte(recolte)
                .quantite(10.0)
                .build();

        DetailRecolteResponse expectedResponse = DetailRecolteResponse.builder()
                .id(detailId)
                .quantite(15.0)
                .build();

        when(detailRepo.findById(detailId)).thenReturn(Optional.of(existingDetail));
        when(detailRepo.save(any(DetailRecolte.class))).thenReturn(existingDetail);
        when(detailMapper.toResponse(any(DetailRecolte.class))).thenReturn(expectedResponse);

        DetailRecolteResponse actualResponse = detailRecolteService.updateDetailRecolte(detailId, request);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse.id(), actualResponse.id());
        assertEquals(expectedResponse.quantite(), actualResponse.quantite());
        assertEquals(15.0, recolte.getQuantiteTotale());


        verify(detailRepo).findById(detailId);
        verify(detailRepo).save(any(DetailRecolte.class));
        verify(detailMapper).toResponse(any(DetailRecolte.class));
    }

    @Test
    void deleteDetailRecolte_Success() {
        int detailId = 1;
        Recolte recolte = Recolte.builder()
                .id(1)
                .quantiteTotale(100.0)
                .build();

        DetailRecolte detailRecolte = DetailRecolte.builder()
                .id(detailId)
                .recolte(recolte)
                .quantite(20.0)
                .build();

        when(detailRepo.findById(detailId)).thenReturn(Optional.of(detailRecolte));

        detailRecolteService.deleteDetailRecolte(detailId);

        assertEquals(80.0, recolte.getQuantiteTotale());

        verify(detailRepo).findById(detailId);
        verify(detailRepo).delete(detailRecolte);
    }

    @Test
    void addDetailRecolte_WhenRecolteNotFound_ThrowsRecolteException() {
        RecolteDetailRequest request = RecolteDetailRequest.builder()
                .recolteId(1)
                .arbreId(1)
                .quantite(10.0)
                .build();

        when(recolteRepo.findById(request.recolteId())).thenReturn(Optional.empty());
        assertThrows(RecolteException.class, () -> detailRecolteService.addDetailRecolte(request));
        verify(recolteRepo).findById(request.recolteId());
    }

    @Test
    void addDetailRecolte_WhenDetailAlreadyExists_ThrowsIllegalArgumentException() {
        RecolteDetailRequest request = RecolteDetailRequest.builder()
                .recolteId(1)
                .arbreId(1)
                .quantite(10.0)
                .build();

        Recolte recolte = Recolte.builder()
                .id(1)
                .saison(Saison.HIVER)
                .build();

        Arbre arbre = Arbre.builder()
                .id(1)
                .build();

        when(recolteRepo.findById(request.recolteId())).thenReturn(Optional.of(recolte));
        when(arbreRepo.findById(request.arbreId())).thenReturn(Optional.of(arbre));
        when(detailRepo.findExistDetailByArbreIdAndSaison(arbre.getId(), recolte.getId(), recolte.getSaison()))
                .thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> detailRecolteService.addDetailRecolte(request));
        verify(detailRepo).findExistDetailByArbreIdAndSaison(arbre.getId(), recolte.getId(), recolte.getSaison());
    }
}
