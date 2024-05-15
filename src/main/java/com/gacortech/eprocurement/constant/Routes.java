package com.gacortech.eprocurement.constant;

public class Routes {
    public static final String VERSION_0    = "/v0";
    public static final String ROOT         = "/api" + VERSION_0;

    public static final String PRODUCTS = ROOT + "/products";
    public static final String CATEGORIES = ROOT + "/categories";
    public static final String ORDERS   = ROOT + "/orders";
    public static final String VENDORS  = ROOT + "/vendors";
    public static final String REPORT   = ROOT + "/report";
    public static final String PATH_VAR_ID = "/{id}";
}
