package dev.anirban.stockbot.util;

public class UrlConstants {

    // Authentication Endpoints
    public static final String LOGIN_EMPLOYEE = "/login";

    // Employee Endpoints (Create)
    public static final String CREATE_EMPLOYEE = "/employees";
    public static final String UPDATE_EMPLOYEE = "/employees";

    // Supplier Endpoints
    public static final String CREATE_SUPPLIER = "/suppliers";
    public static final String FIND_ALL_SUPPLIERS = "/suppliers";
    public static final String FIND_SUPPLIER_BY_ID = "/suppliers/{id}";
    public static final String UPDATE_SUPPLIER = "/suppliers";
    public static final String DELETE_SUPPLIER = "/suppliers/{id}";
}