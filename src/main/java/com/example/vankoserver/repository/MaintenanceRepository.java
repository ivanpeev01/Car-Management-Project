package com.example.vankoserver.repository;


import com.example.vankoserver.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long>, JpaSpecificationExecutor<Maintenance> {



}
