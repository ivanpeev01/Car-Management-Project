package com.example.vankoserver.model.filter;


import lombok.Data;

@Data
public class MaintenanceFIlter {

    private Long carId;
    private Long garageId;
    private String startDate;
    private String endDate;
}
