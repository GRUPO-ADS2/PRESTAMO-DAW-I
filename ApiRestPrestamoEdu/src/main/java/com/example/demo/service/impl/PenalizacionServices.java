package com.example.demo.service.impl;

import com.example.demo.dto.PenaDTO;
import com.example.demo.models.Penalizacion;
import com.example.demo.models.Prestamo;
import com.example.demo.repository.IPenalizacionRepository;
import com.example.demo.repository.IPrestamoRepository;
import com.example.demo.service.IPenalizacionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PenalizacionServices implements IPenalizacionServices {

    IPenalizacionRepository _penalizacionRepository;

    @Autowired
    public PenalizacionServices(IPenalizacionRepository penalizacionRepository){
        _penalizacionRepository = penalizacionRepository;
    }

    @Override
    public List<Penalizacion> GetAllPenalizaciones() {
        return _penalizacionRepository.findAll();
    }

    @Override
    @Transactional
    public void registrarPenalizacion(PenaDTO penaDTO) {
        try {
            LocalDateTime _fechaPenalizacion = LocalDateTime.now();
            _penalizacionRepository.registrarPenalizacion(
                    penaDTO.getPrestamoId(),
                    _fechaPenalizacion,
                    penaDTO.getDescripcion()
            );
        } catch (Exception e) {
            System.err.println("Error al registrar la penalizacion: " + e.getMessage());
        }
    }

    @Override
    public Penalizacion FindPenalizacionById(int id) {
        return _penalizacionRepository.findById(id).orElseGet(Penalizacion::new);
    }

    @Override
    public Integer updatePenalizacion(Integer id, Penalizacion penalizacion) {
        return _penalizacionRepository.findById(id).map(existingPenalizacion -> {
            existingPenalizacion.setFechaPenalizacion(penalizacion.getFechaPenalizacion());
            existingPenalizacion.setDescripcion(penalizacion.getDescripcion());
            _penalizacionRepository.save(existingPenalizacion);
            return 1;
        }).orElse(0);
    }

    @Override
    public Integer deletePenalizacion(Integer id) {
        return _penalizacionRepository.findById(id).map(penalizacion -> {
            _penalizacionRepository.deleteById(id);
            return 1;
        }).orElse(0);
    }

}
