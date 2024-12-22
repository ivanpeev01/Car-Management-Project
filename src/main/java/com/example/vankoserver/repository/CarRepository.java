package com.example.vankoserver.repository;

import com.example.vankoserver.entity.Car;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long>, JpaSpecificationExecutor<Car> {
}
