package com.dantruong.health_declarations.controller;

import com.dantruong.health_declarations.dto.HealthDeclarationDto;
import com.dantruong.health_declarations.service.HealthDeclarationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AppController {
    private final HealthDeclarationService healthDeclarationService;

    public AppController(HealthDeclarationService healthDeclarationService) {
        this.healthDeclarationService = healthDeclarationService;
    }

    @GetMapping("/home")
    public String getForm(Model model){
        model.addAttribute("healthDeclarationDto", new HealthDeclarationDto());
        return "index";
    }

    @PostMapping("/create-form")
    public String createForm(@ModelAttribute("healthDeclarationDto") HealthDeclarationDto healthDeclarationDto, RedirectAttributes redirectAttributes){
        healthDeclarationService.saveAll(healthDeclarationDto);
        redirectAttributes.addAttribute("declarantId", healthDeclarationDto.getHealthDeclarantId());
        return "redirect:/view";
    }

    @GetMapping("/view")
    public String showDeclaration(@RequestParam("declarantId") Integer declarantId, Model model){
        model.addAttribute("healthDeclarationDtoList",  healthDeclarationService.getHealthDeclarationById(declarantId));
        return "view";
    }
}
