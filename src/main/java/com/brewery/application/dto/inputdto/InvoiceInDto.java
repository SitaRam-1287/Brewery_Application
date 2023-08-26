package com.brewery.application.dto.inputdto;

import lombok.Data;

@Data
public class InvoiceInDto {


    private Double amount;

    private Double deliveryFee;

    private Double gst;

}
