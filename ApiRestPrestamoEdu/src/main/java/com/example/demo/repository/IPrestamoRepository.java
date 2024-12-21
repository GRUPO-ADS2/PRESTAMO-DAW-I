package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Prestamo;
@Repository
public interface IPrestamoRepository extends JpaRepository<Prestamo, Integer> {
	@Procedure(name = "registrarPrestamo")
	Prestamo registrarPrestamo(int solicitudId, LocalDateTime fechaPrestamo);
	@Procedure(name = "registrarDevolucion")
	Prestamo registrarDevolucion(int prestamoId, LocalDateTime fechaDevolucion);
	@Procedure(name = "actualizarPrestamo")
	Prestamo actualizarPrestamo(int prestamoId, LocalDateTime nuevaFechaPrestamo);
	
	List<Prestamo> findByEstado(String estado);
}
