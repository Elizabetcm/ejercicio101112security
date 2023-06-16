package com.example.ejercicio101112security.controller;

import com.example.ejercicio101112security.entities.Laptop;
import com.example.ejercicio101112security.repository.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptops")
    public List<Laptop> LaptopList() {
        return laptopRepository.findAll();
    }

    @GetMapping("/api/findLaptopById")
    public ResponseEntity <Laptop> findLaptopById(@PathVariable Long id){
        Optional<Laptop> laptopOpt = laptopRepository.findById(id);
        if(laptopOpt.isPresent())
            return ResponseEntity.ok(laptopOpt.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/laptops")
    public Laptop create(@RequestBody Laptop laptop){
        return laptopRepository.save(laptop);
    }

    @PutMapping("/api/update")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if(laptop.getId() == null){
            log.warn("trying to update a non existing laptop");
            return ResponseEntity.notFound().build();
        }

        if(!laptopRepository.existsById(laptop.getId())){
            log.warn("trying to update a non existing laptop");
            return ResponseEntity.notFound().build();
        }

        Laptop laptop1 = laptopRepository.save(laptop);
        return ResponseEntity.ok(laptop1);
    }

    @DeleteMapping("/api/delete/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        if(!laptopRepository.existsById(id)){
            log.warn("trying to delete a non existing laptop");
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/deleteAll")
    public ResponseEntity<Laptop> deleteAll(){
        log.info("Delete all");
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
