package com.example.vankoserver.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;

    private String model;

    private Integer productionYear;

    private String licensePlate;

    @ManyToMany
    private List<Garage> garages = new ArrayList<>();


}
