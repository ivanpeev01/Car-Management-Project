package com.example.vankoserver.repository;

import com.example.vankoserver.entity.Garage;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarageRepository extends CrudRepository<Garage, Long>, JpaSpecificationExecutor<Garage> {
}
