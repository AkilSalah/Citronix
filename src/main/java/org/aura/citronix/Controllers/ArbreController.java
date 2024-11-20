package org.aura.citronix.Controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aura.citronix.DTO.Request.ArbreRequest;
import org.aura.citronix.DTO.Response.ArbreResponse;
import org.aura.citronix.Services.Interfaces.ArbreInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/arbres")
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArbreController {

    private final ArbreInterface arbreService;

    @GetMapping("/{id}")
    public ResponseEntity<ArbreResponse> getArbre(@PathVariable int id) {
        ArbreResponse arbreResponse = arbreService.getArbresById(id);
        return ResponseEntity.ok(arbreResponse);
    }
    @GetMapping
    public ResponseEntity<List<ArbreResponse>> getAllArbres() {
        List<ArbreResponse> arbreResponses = arbreService.getAllArbres();
        return ResponseEntity.ok(arbreResponses);
    }
    @PostMapping
    public ResponseEntity<ArbreResponse> addArbre(@RequestBody @Valid ArbreRequest arbreRequest) {
        ArbreResponse arbreResponse = arbreService.addArbre(arbreRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(arbreResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ArbreResponse> updateArbre(@PathVariable int id, @RequestBody @Valid ArbreRequest arbreRequest) {
        ArbreResponse arbreResponse = arbreService.updateArbre(arbreRequest,id);
        return ResponseEntity.ok(arbreResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ArbreResponse> deleteArbre(@PathVariable int id) {
        arbreService.deleteArbre(id);
        return ResponseEntity.noContent().build();
    }
}
