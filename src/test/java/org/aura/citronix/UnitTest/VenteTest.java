package org.aura.citronix.UnitTest;

import org.aura.citronix.DTO.Request.VenteRequest;
import org.aura.citronix.DTO.Response.RecolteMinimaleResponse;
import org.aura.citronix.DTO.Response.VenteResponse;
import org.aura.citronix.Entities.Enum.Saison;
import org.aura.citronix.Entities.Recolte;
import org.aura.citronix.Entities.Vente;
import org.aura.citronix.Exceptions.VenteException;
import org.aura.citronix.Mapper.VenteMapper;
import org.aura.citronix.Repositories.RecolteRepo;
import org.aura.citronix.Repositories.VenteRepo;
import org.aura.citronix.Services.Implementation.VenteImpl;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VenteTest {
    @Mock
    private VenteRepo venteRepo;
    @Mock
    private VenteMapper venteMapper;
    @Mock
    private RecolteRepo recolteRepo;
    @InjectMocks
    private VenteImpl venteService;

    @Test
    void addVente_Success() {
        VenteRequest request = VenteRequest.builder()
                .dateDeVente(LocalDate.now())
                .prixUnitaire(10.0)
                .clientName("Client Test")
                .quantiteVendue(50.0)
                .recolteId(1)
                .build();

        Recolte recolte = Recolte.builder()
                .id(1)
                .quantiteTotale(100.0)
                .dateDeRecolte(LocalDate.now())
                .saison(Saison.HIVER)
                .build();

        Vente vente = Vente.builder()
                .id(1)
                .dateDeVente(request.dateDeVente())
                .prixUnitaire(request.prixUnitaire())
                .clientName(request.clientName())
                .quantiteVendue(request.quantiteVendue())
                .recolte(recolte)
                .build();

        RecolteMinimaleResponse recolteResponse = RecolteMinimaleResponse.builder()
                .id(1)
                .dateDeRecolte(recolte.getDateDeRecolte())
                .quantiteTotale(50.0)
                .saison(recolte.getSaison())
                .build();

        VenteResponse expectedResponse = VenteResponse.builder()
                .id(1)
                .dateDeVente(request.dateDeVente())
                .prixUnitaire(request.prixUnitaire())
                .clientName(request.clientName())
                .quantiteVendue(request.quantiteVendue())
                .revenu(500.0)
                .recolteVente(recolteResponse)
                .build();

        when(recolteRepo.findById(anyInt())).thenReturn(Optional.of(recolte));
        when(venteMapper.toEntity(any(VenteRequest.class))).thenReturn(vente);
        when(venteRepo.save(any(Vente.class))).thenReturn(vente);
        when(venteMapper.toVenteResponse(any(Vente.class))).thenReturn(expectedResponse);

        VenteResponse actualResponse = venteService.addVente(request);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse.id(), actualResponse.id());
        assertEquals(expectedResponse.quantiteVendue(), actualResponse.quantiteVendue());
        assertEquals(expectedResponse.prixUnitaire(), actualResponse.prixUnitaire());
        assertEquals(expectedResponse.revenu(), actualResponse.revenu());
        assertEquals(50.0, recolte.getQuantiteTotale());

        verify(recolteRepo).findById(request.recolteId());
        verify(venteMapper).toEntity(request);
        verify(venteRepo).save(any(Vente.class));
        verify(recolteRepo).save(recolte);
        verify(venteMapper).toVenteResponse(any(Vente.class));
    }

    @Test
    void addVente_WhenQuantiteDepasseStock_ThrowsIllegalArgumentException() {
        VenteRequest request = VenteRequest.builder()
                .dateDeVente(LocalDate.now())
                .prixUnitaire(10.0)
                .clientName("Client Test")
                .quantiteVendue(150.0)
                .recolteId(1)
                .build();

        Recolte recolte = Recolte.builder()
                .id(1)
                .quantiteTotale(100.0)
                .build();

        when(recolteRepo.findById(anyInt())).thenReturn(Optional.of(recolte));

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> venteService.addVente(request));
        assertEquals("La quantité demandée dépasse la quantité disponible pour cette récolte.",
                exception.getMessage());
    }

    @Test
    void updateVente_Success() {
        int venteId = 1;
        VenteRequest request = VenteRequest.builder()
                .dateDeVente(LocalDate.now())
                .prixUnitaire(12.0)
                .clientName("Client Updated")
                .quantiteVendue(30.0)
                .recolteId(1)
                .build();

        Recolte recolte = Recolte.builder()
                .id(1)
                .quantiteTotale(50.0)
                .build();

        Vente existingVente = Vente.builder()
                .id(venteId)
                .quantiteVendue(50.0)
                .prixUnitaire(10.0)
                .recolte(recolte)
                .build();

        VenteResponse expectedResponse = VenteResponse.builder()
                .id(venteId)
                .quantiteVendue(30.0)
                .prixUnitaire(12.0)
                .revenu(360.0)
                .clientName("Client Updated")
                .build();

        when(venteRepo.findById(anyInt())).thenReturn(Optional.of(existingVente));
        when(recolteRepo.findById(anyInt())).thenReturn(Optional.of(recolte));
        when(venteMapper.toVenteResponse(any(Vente.class))).thenReturn(expectedResponse);

        VenteResponse actualResponse = venteService.updateVente(venteId, request);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse.quantiteVendue(), actualResponse.quantiteVendue());
        assertEquals(expectedResponse.prixUnitaire(), actualResponse.prixUnitaire());
        assertEquals(expectedResponse.revenu(), actualResponse.revenu());
        assertEquals(70.0, recolte.getQuantiteTotale());

        verify(venteRepo).findById(venteId);
        verify(recolteRepo).findById(request.recolteId());
        verify(venteRepo).save(any(Vente.class));
        verify(recolteRepo).save(recolte);
    }

    @Test
    void deleteVente_Success() {
        int venteId = 1;
        when(venteRepo.existsById(venteId)).thenReturn(true);

        venteService.deleteVente(venteId);

        verify(venteRepo).existsById(venteId);
        verify(venteRepo).deleteById(venteId);
    }

    @Test
    void deleteVente_WhenNotFound_ThrowsVenteException() {
        int venteId = 1;
        when(venteRepo.existsById(venteId)).thenReturn(false);

        assertThrows(VenteException.class, () -> venteService.deleteVente(venteId));
        verify(venteRepo).existsById(venteId);
    }
}
