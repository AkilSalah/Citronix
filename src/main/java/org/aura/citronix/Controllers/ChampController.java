//package org.aura.citronix.Controllers;


import jakarta.validation.Valid;
import org.aura.citronix.DTO.Request.ChampRequest;
import org.aura.citronix.DTO.Response.ChampDto;
//import org.aura.citronix.Services.Interfaces.ChampInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/api/champs")
//public class ChampController {
//    private final ChampInterface champInterface;
//
//    @Autowired
//    public ChampController(ChampInterface champInterface) {
//        this.champInterface = champInterface;
//    }
//    @GetMapping
//    public ResponseEntity<List<ChampDto>>getChamps(){
//        List<ChampDto> champs = champInterface.getAllChamps();
//        if(champs.isEmpty()){
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(champs);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ChampDto> getChampById(@PathVariable int id){
//        ChampDto champDto = champInterface.getChampById(id);
//        if(champDto == null){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(champDto);
//    }
//
//    @PostMapping
//    public ResponseEntity<ChampDto> createChamp(@RequestBody @Valid ChampRequest champRequest) {
//        try {
//            ChampDto savedChamp = champInterface.addChamp(champRequest);
//            return ResponseEntity.status(HttpStatus.CREATED).body(savedChamp);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ChampDto> updateChamp(@PathVariable int id, @RequestBody @Valid ChampRequest champRequest){
//        ChampDto updatedChamp = champInterface.updateChamp(champRequest, id);
//        if(updatedChamp == null){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(updatedChamp);
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ChampDto> deleteChamp(@PathVariable int id){
//        champInterface.deleteChamp(id);
//        return ResponseEntity.noContent().build();
//    }



//}
