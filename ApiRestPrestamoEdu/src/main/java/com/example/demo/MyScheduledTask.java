package com.example.demo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.repository.IPrestamoRepository;

@Component
public class MyScheduledTask {
    @Autowired
    private IPrestamoRepository iPrestamoRepository;
    @Autowired
    
    public void executeTask() throws IOException{
	System.out.println("Ejecutando tarea programada para saciones de alumnos");
	
    }

}
