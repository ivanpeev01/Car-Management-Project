package com.example.vankoserver.service;

import com.example.vankoserver.entity.Maintenance;
import com.example.vankoserver.model.CreateMaintenanceDTO;
import com.example.vankoserver.model.ResponseMaintenanceDTO;
import com.example.vankoserver.model.UpdateMaintenanceDTO;
import com.example.vankoserver.model.filter.MaintenanceFIlter;
import com.example.vankoserver.repository.MaintenanceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    public ResponseMaintenanceDTO getMaintenance(long id) {
        Maintenance maintenance = maintenanceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no maintenance found for id " + id));
        return toDto(maintenance);
    }

    public List<ResponseMaintenanceDTO> getAllMaintenances(MaintenanceFIlter fIlter) {
        List<Maintenance> maintenances = maintenanceRepository.findAll(getFilterSpec(fIlter));
        return maintenances.stream().map(this::toDto).toList();
    }

    public ResponseMaintenanceDTO createMaintenance(CreateMaintenanceDTO createMaintenanceDTO) {

        Maintenance maintenance = toNewMaintenance(createMaintenanceDTO);
        Maintenance maintenanceResponse = maintenanceRepository.save(maintenance);
        return toDto(maintenanceResponse);
    }


    public ResponseMaintenanceDTO updateMaintenance(long id, UpdateMaintenanceDTO updateMaintenanceDTO) {
        Maintenance maintenance = maintenanceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no maintenance found for id " + id));

        maintenance.setCarId(updateMaintenanceDTO.getCarId());
        maintenance.setServiceType(updateMaintenanceDTO.getServiceType());
        maintenance.setGarageId(updateMaintenanceDTO.getGarageId());
        maintenance.setScheduledDate(updateMaintenanceDTO.getScheduledDate());

        Maintenance maintenanceResponse = maintenanceRepository.save(maintenance);

        return toDto(maintenanceResponse);
    }

    public void deleteMaintenance(long id) {
        maintenanceRepository.deleteById(id);
    }

    private Maintenance toNewMaintenance(CreateMaintenanceDTO createMaintenanceDTO) {
        Maintenance maintenance = new Maintenance();
        maintenance.setCarId(createMaintenanceDTO.getCarId());
        maintenance.setServiceType(createMaintenanceDTO.getServiceType());
        maintenance.setGarageId(createMaintenanceDTO.getGarageId());
        maintenance.setScheduledDate(createMaintenanceDTO.getScheduledDate());
        return maintenance;
    }

    private ResponseMaintenanceDTO toDto(Maintenance maintenance) {
        ResponseMaintenanceDTO responseMaintenanceDTO = new ResponseMaintenanceDTO();
        responseMaintenanceDTO.setId(maintenance.getId());
        responseMaintenanceDTO.setCarId(maintenance.getCarId());
        responseMaintenanceDTO.setGarageId(maintenance.getGarageId());
        responseMaintenanceDTO.setServiceType(maintenance.getServiceType());
        responseMaintenanceDTO.setCarName(maintenance.getCarName());
        responseMaintenanceDTO.setGarageName(maintenance.getGarageName());
        responseMaintenanceDTO.setServiceType(maintenance.getServiceType());
        return responseMaintenanceDTO;
    }

    private Specification<Maintenance> getFilterSpec(MaintenanceFIlter fIlter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (fIlter.getCarId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("carId"), fIlter.getCarId()));
            }
            if (fIlter.getGarageId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("garageId"), fIlter.getGarageId()));
            }

            if (fIlter.getStartDate() != null) {
                predicates.add(criteriaBuilder.greaterThan(root.get("scheduledDate"), fIlter.getStartDate()));
            }

            if (fIlter.getEndDate() != null) {
                predicates.add(criteriaBuilder.lessThan(root.get("scheduledDate"), fIlter.getEndDate()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
