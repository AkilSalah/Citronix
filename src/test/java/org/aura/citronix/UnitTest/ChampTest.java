package org.aura.citronix.UnitTest;

import org.aura.citronix.DTO.Request.ChampRequest;
import org.aura.citronix.DTO.Response.ChampResponse;
import org.aura.citronix.DTO.Response.FermeResponse;
import org.aura.citronix.Entities.Champ;
import org.aura.citronix.Entities.Ferme;
import org.aura.citronix.Mapper.ChampMapper;
import org.aura.citronix.Mapper.FermeMapper;
import org.aura.citronix.Repositories.ChampRepo;
import org.aura.citronix.Services.Implementation.ChampImpl;
import org.aura.citronix.Services.Interfaces.FermeInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class ChampTest {

    @Mock
    private ChampRepo champRepo;

    @Mock
    private ChampMapper champMapper;

    @Mock
    private FermeInterface fermeService;

    @Mock
    private FermeMapper fermeMapper;

    @InjectMocks
    private ChampImpl champService;

    @Test
    void testGetChampById() {
        int champId = 1;
        Champ champ = Champ.builder()
                .id(champId)
                .champName("Champ Test")
                .champSurface(1500.0)
                .build();

        ChampResponse champResponse = ChampResponse.builder()
                .id(champId)
                .champName("Champ Test")
                .champSurface(1500.0)
                .arbres(null)
                .ferme(null)
                .build();

        when(champRepo.findById(champId)).thenReturn(Optional.of(champ));
        when(champMapper.toDTO(champ)).thenReturn(champResponse);

        ChampResponse result = champService.getChampById(champId);

        assertNotNull(result);
        assertEquals(champId, result.id());
        assertEquals("Champ Test", result.champName());
        assertEquals(1500.0, result.champSurface());

        verify(champRepo, times(1)).findById(champId);
        verify(champMapper, times(1)).toDTO(champ);
    }

    @Test
    void testAddChamp() {
        ChampRequest request = ChampRequest.builder()
                .champSurface(1500.0)
                .champName("Champ Nouveau")
                .fermeId(1)
                .build();

        FermeResponse fermeResponse = FermeResponse.builder()
                .id(1)
                .superficie(5000.0)
                .name("Ferme Test")
                .build();

        Ferme ferme = Ferme.builder()
                .id(1)
                .superficie(5000.0)
                .champs(new ArrayList<>())
                .build();

        Champ champ = Champ.builder()
                .champName("Champ Nouveau")
                .champSurface(1500.0)
                .ferme(ferme)
                .build();

        Champ savedChamp = Champ.builder()
                .id(1)
                .champName("Champ Nouveau")
                .champSurface(1500.0)
                .ferme(ferme)
                .build();

        ChampResponse expectedResponse = ChampResponse.builder()
                .id(1)
                .champName("Champ Nouveau")
                .champSurface(1500.0)
                .ferme(null)
                .arbres(null)
                .build();

        when(fermeService.getFermeById(1)).thenReturn(fermeResponse);
        when(fermeMapper.responseToEntity(fermeResponse)).thenReturn(ferme);
        when(champMapper.requestToEntity(request)).thenReturn(champ);
        when(champRepo.save(champ)).thenReturn(savedChamp);
        when(champMapper.toDTO(savedChamp)).thenReturn(expectedResponse);

        ChampResponse result = champService.addChamp(request);

        assertNotNull(result);
        assertEquals(1, result.id());
        assertEquals("Champ Nouveau", result.champName());
        assertEquals(1500.0, result.champSurface());
        assertNull(result.ferme());
        assertNull(result.arbres());

        verify(fermeService, times(1)).getFermeById(1);
        verify(fermeMapper, times(1)).responseToEntity(fermeResponse);
        verify(champMapper, times(1)).requestToEntity(request);
        verify(champRepo, times(1)).save(champ);
        verify(champMapper, times(1)).toDTO(savedChamp);
    }

    @Test
    void testUpdateChamp() {
        int champId = 1;

        Champ existingChamp = Champ.builder()
                .id(champId)
                .champName("Champ Ancien")
                .champSurface(1000.0)
                .build();

        ChampRequest request = ChampRequest.builder()
                .champSurface(2000.0)
                .champName("Champ Modifié")
                .fermeId(1)
                .build();

        FermeResponse fermeResponse = FermeResponse.builder()
                .id(1)
                .superficie(5000.0)
                .name("Ferme Test")
                .build();

        Ferme mockFerme = Ferme.builder()
                .id(1)
                .superficie(5000.0)
                .build();

        Champ updatedChamp = Champ.builder()
                .id(champId)
                .champName("Champ Modifié")
                .champSurface(2000.0)
                .ferme(mockFerme)
                .build();

        ChampResponse expectedResponse = ChampResponse.builder()
                .id(champId)
                .champName("Champ Modifié")
                .champSurface(2000.0)
                .ferme(null)
                .arbres(null)
                .build();

        when(champRepo.findById(champId)).thenReturn(Optional.of(existingChamp));
        when(fermeService.getFermeById(1)).thenReturn(fermeResponse);
        when(fermeMapper.responseToEntity(fermeResponse)).thenReturn(mockFerme);
        when(champRepo.save(any(Champ.class))).thenReturn(updatedChamp);
        when(champMapper.toDTO(updatedChamp)).thenReturn(expectedResponse);

        ChampResponse result = champService.updateChamp(request, champId);

        assertNotNull(result);
        assertEquals(champId, result.id());
        assertEquals("Champ Modifié", result.champName());
        assertEquals(2000.0, result.champSurface());

        verify(champRepo, times(1)).findById(champId);
        verify(fermeService, times(1)).getFermeById(1);
        verify(champRepo, times(1)).save(any(Champ.class));
    }

    @Test
    void testDeleteChamp() {
        int champId = 1;

        when(champRepo.existsById(champId)).thenReturn(true);

        champService.deleteChamp(champId);

        verify(champRepo, times(1)).existsById(champId);
        verify(champRepo, times(1)).deleteById(champId);
    }
}
