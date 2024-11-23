package org.aura.citronix.UnitTest;

import org.aura.citronix.DTO.Request.FermeRequest;
import org.aura.citronix.DTO.Response.FermeResponse;
import org.aura.citronix.Entities.Ferme;
import org.aura.citronix.Exceptions.FermeException;
import org.aura.citronix.Mapper.FermeMapper;
import org.aura.citronix.Repositories.FermeRepo;
import org.aura.citronix.Services.Implementation.FermeImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class FermeTest {

    @Mock
    private FermeRepo fermeRepo;

    @Mock
    private FermeMapper fermeMapper;

    @InjectMocks
    private FermeImpl fermeService;

    private Ferme ferme;
    private FermeRequest fermeRequest;
    private FermeResponse fermeResponse;

    @BeforeEach
    void setUp() {
        ferme = Ferme.builder()
                .id(1)
                .name("Ferme Test")
                .localisation("Location Test")
                .dateDeCreation(LocalDate.now())
                .superficie(100.0)
                .build();

        fermeRequest = FermeRequest.builder()
                .name("Ferme Test")
                .localisation("Location Test")
                .dateDeCreation(LocalDate.now())
                .superficie(100.0)
                .build();

        fermeResponse = FermeResponse.builder()
                .id(1)
                .name("Ferme Test")
                .localisation("Location Test")
                .dateDeCreation(LocalDate.now())
                .superficie(100.0)
                .champs(null)
                .build();
    }

    @Test
    void getFermeList() {
        List<Ferme> fermes = List.of(ferme);
        List<FermeResponse> expectedResponses = List.of(fermeResponse);

        when(fermeRepo.findAll()).thenReturn(fermes);
        when(fermeMapper.toDTOList(fermes)).thenReturn(expectedResponses);

        List<FermeResponse> actualResponses = fermeService.getFermeList();

        assertNotNull(actualResponses);
        assertEquals(1, actualResponses.size());
        assertEquals(fermeResponse.name(), actualResponses.get(0).name());
        verify(fermeRepo).findAll();
        verify(fermeMapper).toDTOList(fermes);
    }

    @Test
    void getFermeById_FermeExists() {
        when(fermeRepo.findById(1)).thenReturn(Optional.of(ferme));
        when(fermeMapper.toDTO(ferme)).thenReturn(fermeResponse);

        FermeResponse actual = fermeService.getFermeById(1);

        assertNotNull(actual);
        assertEquals(fermeResponse.id(), actual.id());
        assertEquals(fermeResponse.name(), actual.name());
        verify(fermeRepo).findById(1);
        verify(fermeMapper).toDTO(ferme);
    }

    @Test
    void getFermeById_WhenFermeNotFound() {
        when(fermeRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(FermeException.class, () -> fermeService.getFermeById(1));
        verify(fermeRepo).findById(1);
    }

    @Test
    void addFerme() {
        when(fermeMapper.requestToEntity(fermeRequest)).thenReturn(ferme);
        when(fermeRepo.save(any(Ferme.class))).thenReturn(ferme);
        when(fermeMapper.toDTO(ferme)).thenReturn(fermeResponse);

        FermeResponse actual = fermeService.addFerme(fermeRequest);

        assertNotNull(actual);
        assertEquals(fermeResponse.name(), actual.name());
        verify(fermeMapper).requestToEntity(fermeRequest);
        verify(fermeRepo).save(any(Ferme.class));
        verify(fermeMapper).toDTO(ferme);
    }

    @Test
    void updateFerme() {
        FermeRequest updateRequest = FermeRequest.builder()
                .name("Updated Ferme")
                .localisation("Updated Location")
                .dateDeCreation(LocalDate.now())
                .superficie(150.0)
                .build();

        Ferme updatedFerme = Ferme.builder()
                .id(1)
                .name("Updated Ferme")
                .localisation("Updated Location")
                .dateDeCreation(LocalDate.now())
                .superficie(150.0)
                .build();

        FermeResponse updatedResponse = FermeResponse.builder()
                .id(1)
                .name("Updated Ferme")
                .localisation("Updated Location")
                .dateDeCreation(LocalDate.now())
                .superficie(150.0)
                .champs(null)
                .build();

        when(fermeRepo.findById(1)).thenReturn(Optional.of(ferme));
        when(fermeRepo.save(any(Ferme.class))).thenReturn(updatedFerme);
        when(fermeMapper.toDTO(updatedFerme)).thenReturn(updatedResponse);

        FermeResponse actual = fermeService.updateFerme(updateRequest, 1);

        assertNotNull(actual);
        assertEquals("Updated Ferme", actual.name());
        assertEquals("Updated Location", actual.localisation());
        assertEquals(150.0, actual.superficie());
        verify(fermeRepo).findById(1);
        verify(fermeRepo).save(any(Ferme.class));
        verify(fermeMapper).toDTO(any(Ferme.class));
    }

    @Test
    void deleteFerme() {
        when(fermeRepo.findById(1)).thenReturn(Optional.of(ferme));

        fermeService.deleteFerme(1);

        verify(fermeRepo).findById(1);
        verify(fermeRepo).delete(ferme);
    }
    @Test
    void searchFermes() {
        String name = "Test";
        String localisation = "Location";
        Double superficieMin = 50.0;
        Double superficieMax = 150.0;
        List<Ferme> expectedFermes = Arrays.asList(ferme);

        when(fermeRepo.searchFermes(name, localisation, superficieMin, superficieMax))
                .thenReturn(expectedFermes);

        List<Ferme> actualFermes = fermeService.searchFermes(name, localisation, superficieMin, superficieMax);

        assertNotNull(actualFermes);
        assertEquals(expectedFermes.size(), actualFermes.size());
        verify(fermeRepo).searchFermes(name, localisation, superficieMin, superficieMax);
    }
}
