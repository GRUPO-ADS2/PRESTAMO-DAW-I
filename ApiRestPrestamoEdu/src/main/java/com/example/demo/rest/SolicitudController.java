package com.example.demo.rest;

import com.example.demo.dto.SoliDTO;
import com.example.demo.models.Alumno;
import com.example.demo.models.Material;
import com.example.demo.models.Prestamo;
import com.example.demo.models.Solicitud;
import com.example.demo.service.ISolicitudServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class SolicitudController {

	@Autowired
    ISolicitudServices solicitudServices;

    
    public SolicitudController(ISolicitudServices solicitudServices){this.solicitudServices=solicitudServices;}

    @GetMapping("/solicitudes/{estado}")
    public List<Solicitud> getAll(@PathVariable String estado) {return solicitudServices.GetAllSolicitudes(estado);}

    @GetMapping("/materiales/{page}")
    public Page<Material> getAllMateriales(@PathVariable Integer page) {
    	Pageable pageable = PageRequest.of(page, 15);
    	return solicitudServices.findAll(pageable);
    }

    @GetMapping("/alumnos")
    public List<Alumno> getAllAlumnos() {return solicitudServices.GetAllAlumnos();}

    @GetMapping("/solicitud/{id}")
    public Solicitud getAll(@PathVariable int id) {
        return solicitudServices.FindSolicitudById(id);
    }

    @PostMapping("/solicitud")
    public ResponseEntity<String> registrarSolicitud(@RequestBody SoliDTO soliDTO) {
        try {
            solicitudServices.registrarSolicitud(soliDTO);
            return ResponseEntity.ok("Solicitud registrada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar la solicitud: " + e.getMessage());
        }
    }

@PutMapping("/solicitud/{id}")
    public ResponseEntity<?> actualizarEstadoSolicitud(@PathVariable Integer id, @RequestParam String nuevoEstado) {
        try {
            solicitudServices.actualizarEstadoSolicitud(id, nuevoEstado);
            return ResponseEntity.ok().body("{success: true}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar el estado: " + e.getMessage());
        }
    }

    @DeleteMapping("/solicitud/{id}")
    public ResponseEntity<Integer> deleteSolicitud(@PathVariable Integer id) {
        Integer deleted = solicitudServices.deleteSolicitud(id);
        if (deleted == 1) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
