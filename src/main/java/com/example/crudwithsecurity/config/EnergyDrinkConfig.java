package com.example.crudwithsecurity.config;

import com.example.crudwithsecurity.models.EnergyDrink;
import com.example.crudwithsecurity.services.EnergyDrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
    }
}


@Controller
public class  EnergyDrinkConfig {

    private EnergyDrinkService energyDrinkService;

    public EnergyDrinkConfig(EnergyDrinkService energyDrinkService){
        this.energyDrinkService = energyDrinkService;
    }

    @GetMapping("/drinks/admin")
    public String adminAccess(){
        return "redirect:";
    }

    @GetMapping(value = {"/drinks", "/drinks/"})
    public String getMain(Model model){

        List<EnergyDrink> energyDrinks = energyDrinkService.findAllEnergyDrinks();
        model.addAttribute("drinks", energyDrinks);

        return "index";
    }

    @PostMapping("/drinks")
    public String createDrink(EnergyDrink energyDrink){

        energyDrinkService.saveEnergyDrink(energyDrink);

        return "redirect:/drinks";
    }

    @DeleteMapping("/drinks/{id}")
    public String deleteDrink(@PathVariable int id){
        energyDrinkService.deleteEnergyDrinkById(id);
        return "redirect:/drinks";
    }

    @PutMapping("/drinks/{id}")
    public String updateDrink(@PathVariable("id") int id, @RequestBody EnergyDrink updatedDrink) {
        EnergyDrink existingDrink = energyDrinkService.findByIdEnergyDrink(id);
        if (existingDrink != null) {
            existingDrink.setName(updatedDrink.getName());
            existingDrink.setVolume(updatedDrink.getVolume());
            existingDrink.setDescription(updatedDrink.getDescription());
            existingDrink.setPrice(updatedDrink.getPrice());
            energyDrinkService.saveEnergyDrink(existingDrink);
        }
        return "redirect:/drinks";
    }

}
