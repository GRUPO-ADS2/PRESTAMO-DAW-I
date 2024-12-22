package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Material;
import com.example.demo.service.IMaterialService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/mantenimiento")
public class MaterialController {
    @Autowired
    private IMaterialService _IMaterialService;
    
    @GetMapping("/materiales")
    public String listar(Model model ) {
        model.addAttribute("materiales", _IMaterialService.findAll());
        return "materiales";
    }
    
    @PostMapping("/material")    
    public String guardar(@ModelAttribute Material materialTosave,RedirectAttributes flash) {        
	    if(_IMaterialService.save(materialTosave)!=null)
	    	flash.addFlashAttribute("success",String.format(" El material %s con ID: %d, se guardó con éxito", materialTosave.getNombre(), materialTosave.getCodMaterial()));
	    else 
	    	 flash.addFlashAttribute("error", "error al guardar Material");	    		    	   
        return "redirect:/mantenimiento/materiales";
    }
    @GetMapping("/material/{id}")
    public String getMethodName(@PathVariable Integer id, Model model) {    	
			model.addAttribute("materialTosave", _IMaterialService.getById(id));		
        return "materialToSave";
    }
    
    
    
}
