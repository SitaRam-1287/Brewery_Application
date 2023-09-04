package com.brewery.application.dto.outputdto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DashboardOutputDto {

    private Integer totalOrders;

    private Integer totalCustomers;

    private Double totalRevenue;

    private Integer totalMenuItems;

    private List<String> dates;

    private List<Float> revenue;

    private List<Integer> noOfOrders;


}
