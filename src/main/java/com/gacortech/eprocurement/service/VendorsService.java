package com.gacortech.eprocurement.service;


import com.gacortech.eprocurement.dto.entity_rep.Vendor;
import com.gacortech.eprocurement.entity.Vendors;

import java.util.List;

public interface VendorsService {

    Vendor create(Vendor vendor);
    Vendor update(Vendor vendor);
    Vendor getById(String id);
    Vendors entityById(String id);
    List<Vendor> getAll();

}
