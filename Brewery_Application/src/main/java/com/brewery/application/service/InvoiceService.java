package com.brewery.application.service;


import com.brewery.application.dto.inputdto.InvoiceInDto;
import com.brewery.application.dto.outputdto.InvoiceOutDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface InvoiceService {

    public InvoiceOutDto createInvoice(InvoiceInDto input);

    public InvoiceOutDto getInvoice(UUID id);

    public InvoiceOutDto updateInvoice(InvoiceInDto input);
}
