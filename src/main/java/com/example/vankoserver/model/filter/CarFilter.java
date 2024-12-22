package com.example.vankoserver.model.filter;

import lombok.Data;

@Data
public class CarFilter {

    private String carMake;

    private Long garageId;

    private String fromYear;

    private String toYear;
}
