package org.aura.citronix.Controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aura.citronix.DTO.Request.RecolteDetailRequest;
import org.aura.citronix.DTO.Response.DetailRecolteResponse;
import org.aura.citronix.Services.Interfaces.DetailRecolteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
@RequestMapping("/api/details-recolte")
public class DetailRecolteController {

    private final DetailRecolteInterface detailRecolteService;

    @GetMapping("/recolte/{id}")
    public ResponseEntity<List<DetailRecolteResponse>> getAllDetailRecolteId(@PathVariable int id) {
        List<DetailRecolteResponse> detailForRecolte = detailRecolteService.getAllDetailRecolteByRecolte(id);
        return ResponseEntity.ok(detailForRecolte);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailRecolteResponse> getDetailRecolteById(@PathVariable int id) {
        DetailRecolteResponse detailRecolte = detailRecolteService.getDetailRecolte(id);
        return ResponseEntity.ok(detailRecolte);
    }
    @PostMapping
    public ResponseEntity<DetailRecolteResponse> addDetailRecolte(@RequestBody @Valid  RecolteDetailRequest recolteDetailRequest){
        DetailRecolteResponse detailRecolte = detailRecolteService.addDetailRecolte(recolteDetailRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(detailRecolte);
    }


}
