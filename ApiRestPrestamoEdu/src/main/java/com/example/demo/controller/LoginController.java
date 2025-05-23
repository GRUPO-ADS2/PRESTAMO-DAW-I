package com.example.demo.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LoginController {
    
    @GetMapping({ "/", "/login" })
    public String getMethodName(@RequestParam(required = false) String error, Model model, Principal principal,
	    RedirectAttributes flash) {
	if (principal != null) {
	    flash.addFlashAttribute("info", "ya estas logueado");
	    return "redirect:/";
	}
	if (error != null) {
	    model.addAttribute("error", "usuario o contraseña incorrecta, por favor vuelva a intentar");
	}
	return "login";
    }
}
