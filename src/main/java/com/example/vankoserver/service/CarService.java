package com.example.vankoserver.service;

import com.example.vankoserver.entity.Car;
import com.example.vankoserver.entity.Garage;
import com.example.vankoserver.model.CreateCarDTO;
import com.example.vankoserver.model.ResponseCarDTO;
import com.example.vankoserver.model.UpdateCarDTO;
import com.example.vankoserver.model.filter.CarFilter;
import com.example.vankoserver.repository.CarRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final GarageService garageService;

    public ResponseCarDTO getCarById(long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid car id: " + id));
        return toDto(car);
    }

    public List<ResponseCarDTO> getAllCars(CarFilter filter) {
        List<Car> cars = carRepository.findAll(getFilterSpec(filter));
        return cars.stream().map(this::toDto).toList();
    }

    public ResponseCarDTO createCar(CreateCarDTO createCarDTO) {
        Car car = toCar(createCarDTO);
        Car result = carRepository.save(car);
        return toDto(result);
    }


    public ResponseCarDTO updateCar(long id, UpdateCarDTO updateCarDTO) {
        Car car = carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid car id: " + id));
        car.setMake(updateCarDTO.getMake());
        car.setModel(updateCarDTO.getModel());
        car.setLicensePlate(updateCarDTO.getLicensePlate());
        car.setProductionYear(updateCarDTO.getProductionYear());

        Car saved = carRepository.save(car);
        return toDto(saved);
    }

    public void deleteCar(long id) {
        carRepository.deleteById(id);
    }


    private Car toCar(CreateCarDTO createCarDTO) {
        Car car = new Car();
        car.setMake(createCarDTO.getMake());
        car.setModel(createCarDTO.getModel());
        car.setLicensePlate(createCarDTO.getLicensePlate());
        car.setProductionYear(createCarDTO.getProductionYear());
        car.setGarages(createCarDTO.getGarageIds().stream().map(id -> {
            Garage garage = new Garage();
            garage.setId(id);
            return garage;
        }).collect(Collectors.toList()));
        return car;
    }

    private ResponseCarDTO toDto(Car car) {
        ResponseCarDTO responseCarDTO = new ResponseCarDTO();
        responseCarDTO.setId(car.getId());
        responseCarDTO.setMake(car.getMake());
        responseCarDTO.setModel(car.getModel());
        responseCarDTO.setLicensePlate(car.getLicensePlate());
        responseCarDTO.setProductionYear(car.getProductionYear());
        responseCarDTO.setGarages(car.getGarages().stream().map(garageService::toDto).toList());

        return responseCarDTO;
    }

    private Specification<Car> getFilterSpec(CarFilter filter) {
        return ((root, query, criteriaBuilder) ->
        {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getCarMake() != null) {
                predicates.add(criteriaBuilder.equal(root.get("make"), filter.getCarMake()));
            }
            if (filter.getFromYear() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("productionYear"), filter.getFromYear()));
            }
            if (filter.getToYear() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("productionYear"), filter.getFromYear()));
            }
            if (filter.getGarageId() != null) {
                Join<Car, Garage> carGarages = root.join("garages", JoinType.LEFT);
                predicates.add(carGarages.get("id").in(filter.getGarageId()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

}
