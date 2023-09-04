package com.brewery.application.dto.outputdto;

import lombok.Data;

import java.util.UUID;

@Data
public class InvoiceOutDto {

    private UUID id;

    private Float amount;

    private Float deliveryFee;

    private Float gst;

    private Float totalAmount;

}
