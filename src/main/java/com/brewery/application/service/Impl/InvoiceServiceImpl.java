package com.brewery.application.service.Impl;

import com.brewery.application.dto.inputdto.InvoiceInDto;
import com.brewery.application.dto.inputdto.UserInDto;
import com.brewery.application.dto.outputdto.InvoiceOutDto;
import com.brewery.application.dto.outputdto.UserOutDto;
import com.brewery.application.entity.Invoice;
import com.brewery.application.entity.User;
import com.brewery.application.exception.ElementNotFoundException;
import com.brewery.application.repository.InvoiceRepository;
import com.brewery.application.service.InvoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public InvoiceOutDto createInvoice(InvoiceInDto input) {
        Invoice invoice = convertDtoToEntity(input);
        invoice = invoiceRepository.save(invoice);
        return convertEntityToDto(invoice);
    }

    @Override
    public InvoiceOutDto getInvoice(UUID id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(()->new ElementNotFoundException("Invoice with given id is not found"));
        return convertEntityToDto(invoice);
    }

    @Override
    public InvoiceOutDto updateInvoice(InvoiceInDto input) {
        Invoice invoice = convertDtoToEntity(input);
        Invoice existingInvoice = invoiceRepository.findById(invoice.getId()).orElseThrow(()->new ElementNotFoundException("Invoice with given id is not found"));
        modelMapper.map(invoice,existingInvoice);
        Invoice currentInvoice = invoiceRepository.save(existingInvoice);
        return convertEntityToDto(currentInvoice);
    }

    public Invoice convertDtoToEntity(InvoiceInDto input){

        return modelMapper.map(input,Invoice.class);
    }

    public InvoiceOutDto convertEntityToDto(Invoice invoice){

        return modelMapper.map(invoice,InvoiceOutDto.class);
    }
}
