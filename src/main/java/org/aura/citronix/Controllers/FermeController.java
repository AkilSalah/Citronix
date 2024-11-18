package org.aura.citronix.Controllers;


import jakarta.validation.Valid;
import org.aura.citronix.DTO.Request.FermeRequest;
import org.aura.citronix.DTO.Response.FermeDto;
import org.aura.citronix.Services.Interfaces.FermeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fermes")
public class FermeController {

    private final FermeInterface fermeInterface;

    @Autowired
    public FermeController(FermeInterface fermeInterface) {
        this.fermeInterface = fermeInterface;
    }

    @GetMapping
    public ResponseEntity<List<FermeDto>> getAllFerme() {
        List<FermeDto> fermeList = fermeInterface.getFermeList();
        if (fermeList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(fermeList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<FermeDto> getFermeById(@PathVariable int id) {
        FermeDto ferme = fermeInterface.getFermeById(id);
        if (ferme == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ferme);
    }
    @PostMapping
    public ResponseEntity<FermeDto> createFerme(@RequestBody @Valid FermeRequest fermeRequest) {
        FermeDto ferme = fermeInterface.addFerme(fermeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ferme);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FermeDto> updateFerme(@PathVariable int id, @RequestBody @Valid FermeRequest fermeRequest) {
        FermeDto updatedFerme = fermeInterface.updateFerme(fermeRequest,id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedFerme);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<FermeDto> deleteFerme(@PathVariable int id) {
        fermeInterface.deleteFerme(id);
        return ResponseEntity.noContent().build();
    }
}
