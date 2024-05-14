package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.constant.ResponseMessages;
import com.gacortech.eprocurement.dto.entity_rep.Vendor;
import com.gacortech.eprocurement.entity.Vendors;
import com.gacortech.eprocurement.repository.VendorsRepository;
import com.gacortech.eprocurement.service.VendorsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class VendorsServiceImpl implements VendorsService {

    private final VendorsRepository vendorsRepository;

    @Override
    public Vendors create(Vendor vendor) {

        return vendorsRepository.saveAndFlush(
                Vendors.builder()
                        .nameVendor(vendor.getName())
                        .build()
        );
    }

    @Override
    public Vendors update(Vendor vendor) {
        getById(vendor.getId());
        return vendorsRepository.saveAndFlush(
                Vendors.builder()
                        .id(vendor.getId())
                        .nameVendor(vendor.getName())
                        .build()
        );
    }

    @Override
    public Vendors getById(String id) {
        return vendorsRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessages.ERROR_NOT_FOUND)
                );
    }

    @Override
    public List<Vendors> getAll() {
        return vendorsRepository.findAll();
    }
}
