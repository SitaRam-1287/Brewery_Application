package com.brewery.application.dto.outputdto;

import lombok.Data;

import java.util.UUID;

@Data
public class InvoiceOutDto {

    private UUID id;

    private Double amount;

    private Double deliveryFee;

    private Double gst;

    private Double totalAmount;

}
