package com.example.vankoserver.service;


import com.example.vankoserver.entity.Garage;
import com.example.vankoserver.model.CreateGarageDTO;
import com.example.vankoserver.model.ResponseGarageDTO;
import com.example.vankoserver.model.UpdateGarageDTO;
import com.example.vankoserver.model.filter.GarageFilter;
import com.example.vankoserver.repository.GarageRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GarageService {

    private final GarageRepository garageRepository;


    public ResponseGarageDTO getGarageById(long id) {
        Garage garage = garageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid garage id: " + id));
        return toDto(garage);
    }

    public List<ResponseGarageDTO> getAllGarages(GarageFilter filter) {
        List<Garage> garages = garageRepository.findAll(getFilterSpec(filter));
        return garages.stream().map(this::toDto).toList();
    }

    public ResponseGarageDTO createGarage(CreateGarageDTO createGarageDTO) {
        Garage garage = toGarage(createGarageDTO);
        Garage result = garageRepository.save(garage);
        return toDto(result);
    }


    public ResponseGarageDTO updateGarage(long id, UpdateGarageDTO updateGarageDTO) {
        Garage garage = garageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid garage id: " + id));
        garage.setName(updateGarageDTO.getName());
        garage.setCapacity(updateGarageDTO.getCapacity());
        garage.setCity(updateGarageDTO.getCity());
        garage.setLocation(updateGarageDTO.getLocation());

        Garage saved = garageRepository.save(garage);
        return toDto(saved);
    }

    public void deleteGarage(long id) {
        garageRepository.deleteById(id);
    }



    private Garage toGarage(CreateGarageDTO createGarageDTO) {
        Garage garage = new Garage();
        garage.setName(createGarageDTO.getName());
        garage.setCapacity(createGarageDTO.getCapacity());
        garage.setLocation(createGarageDTO.getLocation());
        garage.setCity(createGarageDTO.getCity());
        return garage;
    }

    ResponseGarageDTO toDto(Garage garage) {
        ResponseGarageDTO responseGarageDTO = new ResponseGarageDTO();
        responseGarageDTO.setId(garage.getId());
        responseGarageDTO.setName(garage.getName());
        responseGarageDTO.setCapacity(garage.getCapacity());
        responseGarageDTO.setCity(garage.getCity());
        responseGarageDTO.setLocation(garage.getLocation());

        return responseGarageDTO;
    }

    private Specification<Garage> getFilterSpec(GarageFilter filter) {
        return ((root, query, criteriaBuilder) ->
        {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getCity() != null) {
                 predicates.add(criteriaBuilder.equal(root.get("city"), filter.getCity()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

}
