package org.aura.citronix.Controllers;


import jakarta.validation.Valid;
import org.aura.citronix.DTO.Request.FermeRequest;
import org.aura.citronix.DTO.Response.FermeResponse;
import org.aura.citronix.Services.Interfaces.FermeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fermes")
@Validated
public class FermeController {

    private final FermeInterface fermeService;

    @Autowired
    public FermeController(FermeInterface fermeService) {
        this.fermeService = fermeService;
    }

    @PostMapping
    public ResponseEntity<FermeResponse> createFerme(@Valid @RequestBody FermeRequest fermeRequest) {
        FermeResponse createdFerme = fermeService.addFerme(fermeRequest);
        return new ResponseEntity<>(createdFerme, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FermeResponse>> getAllFermes() {
        return ResponseEntity.ok(fermeService.getFermeList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FermeResponse> getFermeById(@PathVariable int id) {
        return ResponseEntity.ok(fermeService.getFermeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FermeResponse> updateFerme(@Valid @RequestBody FermeRequest fermeRequest, @PathVariable int id) {
        return ResponseEntity.ok(fermeService.updateFerme(fermeRequest, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFerme(@PathVariable int id) {
        fermeService.deleteFerme(id);
        return ResponseEntity.noContent().build();
    }
}