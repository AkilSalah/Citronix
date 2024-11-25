package org.aura.citronix.UnitTest;

import org.aura.citronix.DTO.Request.ArbreRequest;
import org.aura.citronix.DTO.Response.ArbreResponse;
import org.aura.citronix.DTO.Response.ChampResponse;
import org.aura.citronix.Entities.Arbre;
import org.aura.citronix.Entities.Champ;
import org.aura.citronix.Exceptions.ArbreException;
import org.aura.citronix.Mapper.ArbreMapper;
import org.aura.citronix.Mapper.ChampMapper;
import org.aura.citronix.Repositories.ArbreRepo;
import org.aura.citronix.Services.Implementation.ArbreImpl;
import org.aura.citronix.Services.Interfaces.ChampInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ArbreTest {

    @Mock
    private ArbreRepo arbreRepo;

    @Mock
    private ArbreMapper arbreMapper;

    @Mock
    private ChampInterface champService;

    @Mock
    private ChampMapper champMapper;

    @InjectMocks
    private ArbreImpl arbreService;

    @Test
    void testGetAllArbres_ReturnsListOfArbres() {
        List<Arbre> mockArbres = List.of(
                Arbre.builder().id(1).dateDePlantation(LocalDate.of(2020, 3, 15)).build(),
                Arbre.builder().id(2).dateDePlantation(LocalDate.of(2021, 5, 20)).build()
        );

        List<ArbreResponse> mockResponses = List.of(
                ArbreResponse.builder()
                        .id(1)
                        .dateDePlantation(LocalDate.of(2020, 3, 15))
                        .age(3)
                        .productiviteAnnuelle(10.0)
                        .champ(new ArbreResponse.ChampR(1, "Champ A", 1000.0))
                        .build(),
                ArbreResponse.builder()
                        .id(2)
                        .dateDePlantation(LocalDate.of(2021, 5, 20))
                        .age(2)
                        .productiviteAnnuelle(12.0)
                        .champ(new ArbreResponse.ChampR(2, "Champ B", 2000.0))
                        .build()
        );

        when(arbreRepo.findAll()).thenReturn(mockArbres);
        when(arbreMapper.toResponseList(mockArbres)).thenReturn(mockResponses);

        List<ArbreResponse> result = arbreService.getAllArbres();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).id());
        assertEquals("Champ A", result.get(0).champ().champName());
        verify(arbreRepo, times(1)).findAll();
        verify(arbreMapper, times(1)).toResponseList(mockArbres);
    }

    @Test
    void testGetArbresById_ValidId_ReturnsArbre() {
        int arbreId = 1;
        Arbre mockArbre = Arbre.builder()
                .id(arbreId)
                .dateDePlantation(LocalDate.of(2020, 3, 15))
                .build();

        ArbreResponse mockResponse = ArbreResponse.builder()
                .id(arbreId)
                .dateDePlantation(LocalDate.of(2020, 3, 15))
                .age(3)
                .productiviteAnnuelle(10.0)
                .champ(new ArbreResponse.ChampR(1, "Champ A", 1000.0))
                .build();

        when(arbreRepo.findById(arbreId)).thenReturn(Optional.of(mockArbre));
        when(arbreMapper.toResponse(mockArbre)).thenReturn(mockResponse);

        ArbreResponse result = arbreService.getArbresById(arbreId);

        assertNotNull(result);
        assertEquals(arbreId, result.id());
        assertEquals("Champ A", result.champ().champName());
        verify(arbreRepo, times(1)).findById(arbreId);
        verify(arbreMapper, times(1)).toResponse(mockArbre);
    }

    @Test
    void testAddArbre() {
        ArbreRequest request = ArbreRequest.builder()
                .dateDePlantation(LocalDate.of(2023, 3, 15))
                .champId(1)
                .build();

        ChampResponse champResponse = ChampResponse.builder()
                .id(1)
                .champSurface(1000.0)
                .build();

        Champ mockChamp = Champ.builder()
                .id(1)
                .champSurface(1000.0)
                .arbres(new ArrayList<>())
                .build();

        Arbre mockArbre = Arbre.builder()
                .dateDePlantation(LocalDate.of(2023, 3, 15))
                .champ(mockChamp)
                .build();

        Arbre savedArbre = Arbre.builder()
                .id(1)
                .dateDePlantation(LocalDate.of(2023, 3, 15))
                .champ(mockChamp)
                .build();

        ArbreResponse mockResponse = ArbreResponse.builder()
                .id(1)
                .dateDePlantation(LocalDate.of(2023, 3, 15))
                .age(1)
                .productiviteAnnuelle(10.0)
                .champ(new ArbreResponse.ChampR(1, "Champ A", 1000.0))
                .build();

        when(champService.getChampById(1)).thenReturn(champResponse);
        when(champMapper.responseToEntity(champResponse)).thenReturn(mockChamp);
        when(arbreMapper.requestToEntity(request)).thenReturn(mockArbre);
        when(arbreRepo.save(mockArbre)).thenReturn(savedArbre);
        when(arbreMapper.toResponse(savedArbre)).thenReturn(mockResponse);

        ArbreResponse result = arbreService.addArbre(request);

        assertNotNull(result);
        assertEquals(1, result.id());
        assertEquals(1, result.age());
        assertEquals("Champ A", result.champ().champName());
        verify(champService, times(1)).getChampById(1);
        verify(champMapper, times(1)).responseToEntity(champResponse);
        verify(arbreRepo, times(1)).save(mockArbre);
        verify(arbreMapper, times(1)).toResponse(savedArbre);
    }

    @Test
    void testDeleteArbre() {
        int arbreId = 1;

        when(arbreRepo.existsById(arbreId)).thenReturn(true);

        arbreService.deleteArbre(arbreId);

        verify(arbreRepo, times(1)).existsById(arbreId);
        verify(arbreRepo, times(1)).deleteById(arbreId);
    }

    @Test
    void testDeleteArbre_ThrowsException() {
        int arbreId = 1;

        when(arbreRepo.existsById(arbreId)).thenReturn(false);

        Exception exception = assertThrows(ArbreException.class, () -> {
            arbreService.deleteArbre(arbreId);
        });

        assertTrue(exception.getMessage().contains("Arbre n'exist pas avec id : " + arbreId));
        verify(arbreRepo, times(1)).existsById(arbreId);
        verify(arbreRepo, never()).deleteById(arbreId);
    }
}
