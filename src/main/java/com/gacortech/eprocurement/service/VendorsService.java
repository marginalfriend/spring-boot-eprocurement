package com.gacortech.eprocurement.service;


import com.gacortech.eprocurement.entity.Vendors;

import java.util.List;

public interface VendorsService {

    Vendors create(Vendors vendors);
    Vendors update(Vendors vendors);
    Vendors getById(String id);
    List<Vendors> getAll();

}
