package com.example.demo.service.impl;

import com.example.demo.dto.SoliDTO;
import com.example.demo.models.Alumno;
import com.example.demo.models.Material;
import com.example.demo.models.Solicitud;
import com.example.demo.repository.IAlumnoRepository;
import com.example.demo.repository.IMaterialRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ISolicitudRepository;
import com.example.demo.repository.IUsuarioRepository;
import com.example.demo.service.ISolicitudServices;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SolicitudServices implements ISolicitudServices {

    ISolicitudRepository _solicitudRepository;
    IAlumnoRepository _alumnoRepository;
    IMaterialRepository _materialRepository;
    IUsuarioRepository _IUsuarioRepository;
    
    public SolicitudServices(ISolicitudRepository solicitudRepository,IAlumnoRepository alumnoRepository,IMaterialRepository materialRepository , IUsuarioRepository usuarioRepository){
        _solicitudRepository = solicitudRepository;
        _alumnoRepository = alumnoRepository;
        _materialRepository = materialRepository;
        _IUsuarioRepository = usuarioRepository;
    }
    @Override
    public List<Solicitud> GetAllSolicitudes(String estado) {
    	System.out.println(_solicitudRepository.findByEstado("Generado").size());
        return _solicitudRepository.findByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Material> findAll(Pageable pageable) {
        return _materialRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> GetAllAlumnos() {
        return _alumnoRepository.findAll();
    }

    @Override
    @Transactional
    public Solicitud SaveSolicitud(Solicitud entity) {
        Solicitud solicitudSaved = _solicitudRepository.save(entity);
        return solicitudSaved;
    }

    @Override
    @Transactional(readOnly = true)
    public Solicitud FindSolicitudById(int id) {
        return _solicitudRepository.findById(id).orElseGet(Solicitud::new);
    }

    @Override
    @Transactional
    public void registrarSolicitud(SoliDTO soliDTO) {
        try {
            _IUsuarioRepository.findByUsername(soliDTO.getUsername())
                    .ifPresent(usuario -> _solicitudRepository.registrarSolicitud(
                            usuario.getCodUsuario(),
                            soliDTO.getMaterialCod(),
                            soliDTO.getCantidad()
                    ));
        } catch (Exception e) {
            System.err.println("Error al registrar la solicitud: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void actualizarEstadoSolicitud(Integer solicitudId, String nuevoEstado) {
        _solicitudRepository.actualizarEstadoSolicitud(solicitudId, nuevoEstado);
    }

    @Override
    @Transactional
    public Integer updateSolicitud(Integer id, Solicitud solicitud) {
        return _solicitudRepository.findById(id).map(existingSolicitud -> {
            existingSolicitud.setCantidad(solicitud.getCantidad());
            existingSolicitud.setMaterial(solicitud.getMaterial());
            _solicitudRepository.save(existingSolicitud);
            return 1;
        }).orElse(0);
    }

    @Override
    @Transactional
    public Integer deleteSolicitud(Integer id) {
        return _solicitudRepository.findById(id).map(solicitud -> {
            _solicitudRepository.deleteById(id);
            return 1;
        }).orElse(0);
    }

}
