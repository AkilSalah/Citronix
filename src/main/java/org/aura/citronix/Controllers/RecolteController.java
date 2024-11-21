package org.aura.citronix.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aura.citronix.DTO.Request.RecolteRequest;
import org.aura.citronix.DTO.Response.RecolteResponse;
import org.aura.citronix.Services.Interfaces.RecolteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@RequestMapping("/api/recoltes")
public class RecolteController {
    private final RecolteInterface recolteService;

    @GetMapping
    public ResponseEntity<List<RecolteResponse>> getAllRecoltes() {
        return ResponseEntity.ok(recolteService.getAllRecoltes());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RecolteResponse> getRecolteById(@PathVariable int id) {
        return ResponseEntity.ok(recolteService.getRecolteById(id));
    }
    @PostMapping
    public ResponseEntity<RecolteResponse> createRecolte(@RequestBody @Valid RecolteRequest recolteRequest) {
        return ResponseEntity.ok(recolteService.createRecolte(recolteRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<RecolteResponse> updateRecolte (@PathVariable int id, @RequestBody @Valid RecolteRequest recolteRequest) {
        return ResponseEntity.ok(recolteService.updateRecolte(id, recolteRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<RecolteResponse> deleteRecolte (@PathVariable int id) {
        recolteService.deleteRecolte(id);
        return ResponseEntity.noContent().build();
    }

}
