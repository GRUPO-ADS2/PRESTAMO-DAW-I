package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Material;
import com.example.demo.service.IMaterialService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MaterialController {
    @Autowired
    private IMaterialService _IMaterialService;
    
    @GetMapping("/materiales")
    public String listar(Model model) {
        model.addAttribute("materiales", _IMaterialService.findAll());
        return "materiales";
    }
    
    @PostMapping("/material")    
    public String guardar(@RequestBody Material material,RedirectAttributes flash) {        
	    if(_IMaterialService.save(material)!=null)
	    	flash.addFlashAttribute("success",String.format("3s% con ID: d% se guardó con éxito", material.getNombre(), material.getCodMaterial()));
	    else 
	    	 flash.addFlashAttribute("error", "error al guardar Material");	    		    	   
        return "redirect:/material";
    }
    @PutMapping("/material")
    public String actualizar(@RequestBody Material material,RedirectAttributes flash) {        
	    if(_IMaterialService.update(material)!=null)
	    	flash.addFlashAttribute("success",String.format("3s% con ID: d% se actualizó con éxito", material.getNombre(), material.getCodMaterial()));
	    else 
	    	 flash.addFlashAttribute("error", "error al actualizar Material");	    		    	   
        return "redirect:/material";
    }
    
    
}
