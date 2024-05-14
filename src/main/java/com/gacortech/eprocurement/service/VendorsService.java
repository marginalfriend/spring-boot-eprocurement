package com.gacortech.eprocurement.service;


import com.gacortech.eprocurement.dto.entity_rep.Vendor;
import com.gacortech.eprocurement.entity.Vendors;

import java.util.List;

public interface VendorsService {

    Vendors create(Vendor vendor);
    Vendors update(Vendor vendor);
    Vendors getById(String id);
    List<Vendors> getAll();

}
