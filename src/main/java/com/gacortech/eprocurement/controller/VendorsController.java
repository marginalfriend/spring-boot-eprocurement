package com.gacortech.eprocurement.controller;

import com.gacortech.eprocurement.constant.Routes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = Routes.VENDORS)
public class VendorsController {

}
