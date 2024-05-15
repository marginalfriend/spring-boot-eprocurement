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
    public Vendor create(Vendor vendor) {
        vendorsRepository.saveAndFlush(
                Vendors.builder()
                        .nameVendor(vendor.getName())
                        .build()
        );
        return vendor;
    }

    @Override
    public Vendor update(Vendor vendor) {
        entityById(vendor.getId());
        vendorsRepository.saveAndFlush(
                Vendors.builder()
                        .id(vendor.getId())
                        .nameVendor(vendor.getName())
                        .build()
        );
        return vendor;
    }

    @Override
    public Vendor getById(String id) {
        Vendors vendor = vendorsRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessages.ERROR_NOT_FOUND)
                );
        return Vendor.builder()
                .id(vendor.getId())
                .name(vendor.getNameVendor())
                .build();
    }

    @Override
    public Vendors entityById(String id) {
        return vendorsRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessages.ERROR_NOT_FOUND)
                );
    }

    @Override
    public List<Vendor> getAll() {
        return vendorsRepository.findAll().stream().map(
                detail -> Vendor.builder()
                        .name(detail.getNameVendor())
                        .build()
        ).toList();
    }
}
