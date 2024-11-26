package com.example.demo.service.impl;

import com.example.demo.dto.PresDTO;
import com.example.demo.models.Prestamo;
import com.example.demo.models.Solicitud;
import com.example.demo.repository.IPrestamoRepository;
import com.example.demo.repository.ISolicitudRepository;
import com.example.demo.service.IPrestamoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoServices implements IPrestamoServices {
    IPrestamoRepository _prestamoRepository;

    @Autowired
    public PrestamoServices(IPrestamoRepository prestamoRepository){_prestamoRepository = prestamoRepository;}


    @Override
    public List<Prestamo> GetAllPrestamos() {
        return _prestamoRepository.findAll();
    }

    @Override
    @Transactional
    public Prestamo registrarPrestamo(PresDTO presDTO) {
        try {
            LocalDateTime fechaPrestamoActual = LocalDateTime.now();

            return _prestamoRepository.registrarPrestamo(
                    presDTO.getSolicitudId(),
                    fechaPrestamoActual
            );
        } catch (Exception e) {
            System.err.println("Error al registrar el prestamo: " + e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional
    public Prestamo actualizarPrestamo(int prestamoId, LocalDateTime _nuevaFechaPrestamo) {
        LocalDateTime nuevaFechaPrestamo = LocalDateTime.now();
        return _prestamoRepository.actualizarPrestamo(prestamoId, nuevaFechaPrestamo);
    }


    @Override
    public Prestamo FindPrestamoById(int id) {
        return _prestamoRepository.findById(id).orElseGet(Prestamo::new);
    }

    @Override
    @Transactional
    public Prestamo registrarDevolucion(int prestamoId, LocalDateTime fechaDevolucion) {
        try {
            LocalDateTime _fechaDevolucion = LocalDateTime.now();
            return _prestamoRepository.registrarDevolucion(
                    prestamoId,_fechaDevolucion
            );
        } catch (Exception e) {
            System.err.println("Error al registrar la devolución del prestamo: "
                    + e.getMessage());
        }
        return null;
    }


    @Override
    public Integer deletePrestamo(Integer id) {
        return _prestamoRepository.findById(id).map(prestamo -> {
            _prestamoRepository.deleteById(id);
            return 1;
        }).orElse(0);
    }
}
