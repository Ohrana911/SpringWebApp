package com.example.crudwithsecurity.services;

import com.example.crudwithsecurity.models.EnergyDrink;
import com.example.crudwithsecurity.repositories.EnergyDrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnergyDrinkService{
    private EnergyDrinkRepository energyDrinkRepository;

    @Autowired
    public EnergyDrinkService(EnergyDrinkRepository energyDrinkRepository){
        this.energyDrinkRepository = energyDrinkRepository;
    }

    public List<EnergyDrink> findAllEnergyDrinks(){
        return energyDrinkRepository.findAll();
    }

    public EnergyDrink findByIdEnergyDrink(int id){
        return energyDrinkRepository.getReferenceById(id);
    }

    public EnergyDrink saveEnergyDrink(EnergyDrink energyDrink){
        return energyDrinkRepository.save(energyDrink);
    }

    public void deleteEnergyDrink(EnergyDrink energyDrink){
        energyDrinkRepository.delete(energyDrink);
    }

    public void deleteEnergyDrinkById(int id){
        energyDrinkRepository.deleteById(id);
    }

}
