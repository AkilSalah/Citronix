package org.aura.citronix.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aura.citronix.DTO.Request.VenteRequest;
import org.aura.citronix.DTO.Response.VenteResponse;
import org.aura.citronix.Services.Interfaces.VenteInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/ventes")
public class VenteController {

    private final VenteInterface venteService;
    @GetMapping
    public ResponseEntity<List<VenteResponse>> getAllVentes() {
        return ResponseEntity.ok(venteService.getAllVentes());
    }
    @GetMapping("/{id}")
    public ResponseEntity<VenteResponse> getVenteById(@PathVariable int id) {
        return ResponseEntity.ok(venteService.getVenteById(id));
    }
    @PostMapping
    public ResponseEntity<VenteResponse> createVente(@RequestBody @Valid VenteRequest venteRequest) {
        VenteResponse createdVente = venteService.addVente(venteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VenteResponse> updateVente(@PathVariable int id, @RequestBody @Valid VenteRequest venteRequest) {
        VenteResponse updatedVente = venteService.updateVente(id, venteRequest);
        return ResponseEntity.ok(updatedVente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VenteResponse> deleteVente(@PathVariable int id) {
        venteService.deleteVente(id);
        return ResponseEntity.noContent().build();
    }
}
