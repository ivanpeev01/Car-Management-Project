package com.example.vankoserver.controller;


import com.example.vankoserver.model.CreateMaintenanceDTO;
import com.example.vankoserver.model.ResponseMaintenanceDTO;
import com.example.vankoserver.model.UpdateMaintenanceDTO;
import com.example.vankoserver.model.filter.MaintenanceFIlter;
import com.example.vankoserver.service.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/maintenance")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @GetMapping
    public List<ResponseMaintenanceDTO> getAllMaintenance(@ParameterObject MaintenanceFIlter filter) {
        return maintenanceService.getAllMaintenances(filter);
    }

    @GetMapping("/{id}")
    public ResponseMaintenanceDTO getMaintenance(@PathVariable long id) {
        return maintenanceService.getMaintenance(id);
    }

    @PostMapping()
    public ResponseMaintenanceDTO createMaintenance(@RequestBody CreateMaintenanceDTO createMaintenanceDTO) {
        return maintenanceService.createMaintenance(createMaintenanceDTO);
    }

    @PutMapping("/{id}")
    public ResponseMaintenanceDTO updateMaintenance(@PathVariable long id, @RequestBody UpdateMaintenanceDTO updateMaintenanceDTO) {
        return maintenanceService.updateMaintenance(id,updateMaintenanceDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMaintenance(@PathVariable long id) {
        maintenanceService.deleteMaintenance(id);
    }

}
