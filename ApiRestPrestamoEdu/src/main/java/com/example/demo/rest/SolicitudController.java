package com.example.demo.rest;

import com.example.demo.dto.SoliDTO;
import com.example.demo.models.Alumno;
import com.example.demo.models.Material;
import com.example.demo.models.Solicitud;
import com.example.demo.service.ISolicitudServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class SolicitudController {
   
    private final SimpMessagingTemplate messagingTemplate;
    private ISolicitudServices solicitudServices;
    
    public SolicitudController(ISolicitudServices solicitudServices, SimpMessagingTemplate messagingTemplate) {
        this.solicitudServices = solicitudServices;
        this.messagingTemplate = messagingTemplate;
    }


	@GetMapping("/solicitudes/{estado}")
	public List<Solicitud> getAll(@PathVariable String estado) {
		return solicitudServices.GetAllSolicitudes(estado);
	}

	@GetMapping("/materiales/{page}/{cat}")
	public Page<Material> getAllMateriales(@PathVariable Integer page, @PathVariable String cat,
			@RequestParam (required = false) String nom) {
		Pageable pageable = PageRequest.of(page, 10);
		return solicitudServices.findAll(cat, nom, pageable);
	}

	@GetMapping("/alumnos")
	public List<Alumno> getAllAlumnos() {
		return solicitudServices.GetAllAlumnos();
	}

	@GetMapping("/solicitud/{id}")
	public Solicitud getAll(@PathVariable int id) {
		return solicitudServices.FindSolicitudById(id);
	}

	@PostMapping("/solicitud")
	public ResponseEntity<String> registrarSolicitud(@RequestBody SoliDTO soliDTO) {
		try {
			solicitudServices.registrarSolicitud(soliDTO);
			System.err.println("notificando admin...");
			notificarAdministradores(soliDTO);
			return ResponseEntity.ok("Solicitud registrada");
		} catch (Exception e) {		   
			return ResponseEntity.badRequest().body("Error al registrar la solicitud: " + e.getMessage());
		}
	}
	private void notificarAdministradores(SoliDTO soliDTO) {
	    // Crear un mensaje para los administradores
	    
	    String mensaje = "Nueva solicitud!, materiales: " + soliDTO.getCantidad();
	    // Enviar el mensaje al canal de administradores
	    messagingTemplate.convertAndSend("/chat/admin", mensaje);
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
