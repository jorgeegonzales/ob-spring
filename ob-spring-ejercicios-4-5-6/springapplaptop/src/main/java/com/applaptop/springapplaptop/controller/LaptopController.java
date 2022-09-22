package com.applaptop.springapplaptop.controller;

import com.applaptop.springapplaptop.entities.Laptop;
import com.applaptop.springapplaptop.repository.LaptopRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class LaptopController {

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptops")
    public List<Laptop> findAll() {
        return laptopRepository.findAll();
    }

    @PostMapping("/api/laptops")
    public Laptop create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers) {
        System.out.println(headers.get("User-Agent"));
        //guardar el libro recibido por parametro en la base de datos
        return laptopRepository.save(laptop);

    }


}
