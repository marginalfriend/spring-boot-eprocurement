package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.entity.Vendors;
import com.gacortech.eprocurement.repository.VendorsRepository;
import com.gacortech.eprocurement.service.VendorsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VendorsImpl implements VendorsService {

    private final VendorsRepository vendorsRepository;

    @Override
    public Vendors create(Vendors vendors) {
        return vendorsRepository.saveAndFlush(vendors);
    }

    @Override
    public Vendors update(Vendors vendors) {
        getById(vendors.getId());
        return vendorsRepository.saveAndFlush(vendors);
    }

    @Override
    public Vendors getById(String id) {
        return vendorsRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Vendors Not Found")
                );
    }

    @Override
    public List<Vendors> getAll() {
        return vendorsRepository.findAll();
    }
}
