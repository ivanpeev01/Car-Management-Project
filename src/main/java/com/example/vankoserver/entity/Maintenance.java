package com.example.vankoserver.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long carId;

    private String carName;

    private String serviceType;

    private LocalDate scheduledDate;

    private Long garageId;

    private String garageName;

}
