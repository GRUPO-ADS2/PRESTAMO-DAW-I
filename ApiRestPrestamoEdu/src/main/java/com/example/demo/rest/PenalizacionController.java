package com.example.demo.rest;

import com.example.demo.dto.PenaDTO;
import com.example.demo.dto.PresDTO;
import com.example.demo.models.Penalizacion;
import com.example.demo.models.Prestamo;
import com.example.demo.service.IPenalizacionServices;
import com.example.demo.service.IPrestamoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class PenalizacionController {

    IPenalizacionServices penalizacionServices;
    @Autowired
    public PenalizacionController(IPenalizacionServices penalizacionServices){
        this.penalizacionServices=penalizacionServices;
    }

    @GetMapping("/penalizaciones")
    public List<Penalizacion> getAll() {return penalizacionServices.GetAllPenalizaciones();}

    @GetMapping("/penalizacion/{id}")
    public Penalizacion getAll(@PathVariable int id) {
        return penalizacionServices.FindPenalizacionById(id);
    }

    @PostMapping("/penalizacion")
    public ResponseEntity<String> registrarPenalizacion(@RequestBody PenaDTO penaDTO) {
        try {
            penalizacionServices.registrarPenalizacion(penaDTO);
            return ResponseEntity.ok("Prestamo penalizado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar el préstamo: " + e.getMessage());
        }
    }

    @PutMapping("/penalizacion/{id}")
    public ResponseEntity<Integer> updatePenalizacion(@PathVariable Integer id, @RequestBody Penalizacion penalizacion) {
        Integer updated = penalizacionServices.updatePenalizacion(id, penalizacion);
        if (updated == 1) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/penalizacion/{id}")
    public ResponseEntity<Integer> deletePenalizacion(@PathVariable Integer id) {
        Integer deleted = penalizacionServices.deletePenalizacion(id);
        if (deleted == 1) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
