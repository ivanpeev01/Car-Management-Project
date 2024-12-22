package com.example.vankoserver.controller;


import com.example.vankoserver.model.CreateGarageDTO;
import com.example.vankoserver.model.ResponseGarageDTO;
import com.example.vankoserver.model.UpdateGarageDTO;
import com.example.vankoserver.model.filter.GarageFilter;
import com.example.vankoserver.service.GarageService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/garages")
public class GarageController {

    private final GarageService garageService;


    @GetMapping("/{id}")
    public ResponseGarageDTO getGarageById(@PathVariable long id) {
        return garageService.getGarageById(id);
    }

    @GetMapping
    public List<ResponseGarageDTO> getAllGarages(@ParameterObject GarageFilter filter) {
        return garageService.getAllGarages(filter);
    }

    @PostMapping
    public ResponseGarageDTO createGarage(@RequestBody CreateGarageDTO createGarageDTO) {
        return garageService.createGarage(createGarageDTO);
    }

    @PutMapping("{id}")
    public ResponseGarageDTO updateGarage(@PathVariable long id, @RequestBody UpdateGarageDTO updateGarageDTO) {
        return garageService.updateGarage(id, updateGarageDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteGarage(@PathVariable long id) {
        garageService.deleteGarage(id);
    }
}
