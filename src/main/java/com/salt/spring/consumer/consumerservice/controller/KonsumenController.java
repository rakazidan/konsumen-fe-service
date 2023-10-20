package com.salt.spring.consumer.consumerservice.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.salt.spring.consumer.consumerservice.model.Konsumen;
import com.salt.spring.consumer.consumerservice.service.KonsumenService;

import org.springframework.ui.Model;

@Controller
public class KonsumenController {

    private KonsumenService konsumenService;

    public KonsumenController(KonsumenService konsumenService) {
        this.konsumenService = konsumenService;
    }
    
    @RequestMapping(path="/konsumen", method=RequestMethod.GET)
    public String getKonsumen(Model model) {
        List<Konsumen> konsumen = konsumenService.getKonsumen();
        model.addAttribute("konsumen", konsumen);

        return "konsumen";
    }

    @RequestMapping(path="/konsumen/add", method=RequestMethod.GET)
    public String addKonsumen(Model model) {
        Konsumen konsumen = new Konsumen();
        model.addAttribute("konsumen", konsumen);

        return "konsumen-form";
    }

    @PostMapping("/save")
    public String saveKonsumen(@ModelAttribute("konsumen") Konsumen konsumen) {
        konsumenService.createKonsumen(konsumen);

        return "redirect:/konsumen";
    }

    @RequestMapping(path="/konsumen/edit/{id}", method=RequestMethod.GET)
    public String editKonsumen(@PathVariable(value = "id") Integer id, Model model) {
        Konsumen konsumen = konsumenService.getKonsumenById(id);
        model.addAttribute("konsumen", konsumen);

        return "konsumen-form-edit";
    }

    @PostMapping("/update")
    public String updateKonsumen(@ModelAttribute("konsumen") Konsumen konsumen) {
        konsumenService.updateKonsumen(konsumen);

        return "redirect:/konsumen";
    }

    @GetMapping("/deleteKonsumen/{id}")
    public String deleteThroughId(@PathVariable(value = "id") Integer id) {
        konsumenService.deleteKonsumen(id);

        return "redirect:/konsumen";
 
    }
}
