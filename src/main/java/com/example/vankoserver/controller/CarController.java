package com.example.vankoserver.controller;


import com.example.vankoserver.model.CreateCarDTO;
import com.example.vankoserver.model.ResponseCarDTO;
import com.example.vankoserver.model.UpdateCarDTO;
import com.example.vankoserver.model.filter.CarFilter;
import com.example.vankoserver.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;


    @GetMapping("/{id}")
    public ResponseCarDTO getCarById(@PathVariable long id) {
        return carService.getCarById(id);
    }

    @GetMapping
    public List<ResponseCarDTO> getAllCars(@ParameterObject CarFilter filter) {
        return carService.getAllCars(filter);
    }

    @PostMapping
    public ResponseCarDTO createCar(@RequestBody CreateCarDTO createCarDTO) {
        return carService.createCar(createCarDTO);
    }

    @PutMapping("{id}")
    public ResponseCarDTO updateCar(@PathVariable long id, @RequestBody UpdateCarDTO updateCarDTO) {
        return carService.updateCar(id, updateCarDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable long id) {
        carService.deleteCar(id);
    }
}
