package org.aura.citronix.Controllers;


import jakarta.validation.Valid;
import org.aura.citronix.DTO.Request.ChampRequest;
import org.aura.citronix.DTO.Response.ChampResponse;
import org.aura.citronix.Services.Interfaces.ChampInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/champs")
public class ChampController {
    private final ChampInterface champInterface;

    @Autowired
    public ChampController(ChampInterface champInterface) {
        this.champInterface = champInterface;
    }
    @GetMapping
    public ResponseEntity<List<ChampResponse>>getChamps(){
        List<ChampResponse> champs = champInterface.getAllChamps();
        if(champs.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(champs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChampResponse> getChampById(@PathVariable int id){
        ChampResponse champDto = champInterface.getChampById(id);
        if(champDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(champDto);
    }

    @PostMapping
    public ResponseEntity<ChampResponse> createChamp(@RequestBody @Valid ChampRequest champRequest) {
        ChampResponse champDto = champInterface.addChamp(champRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(champDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChampResponse> updateChamp(@PathVariable int id, @RequestBody @Valid ChampRequest champRequest){
        ChampResponse updatedChamp = champInterface.updateChamp(champRequest, id);
        if(updatedChamp == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedChamp);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ChampResponse> deleteChamp(@PathVariable int id){
        champInterface.deleteChamp(id);
        return ResponseEntity.noContent().build();
    }



}
