package dev.anirban.stockbot.util;

public class UrlConstants {

    // Authentication Endpoints
    public static final String LOGIN_EMPLOYEE = "/login";

    // Employee Endpoints (Create)
    public static final String CREATE_EMPLOYEE = "/employees";
    public static final String UPDATE_EMPLOYEE = "/employees";
    public static final String FIND_EMPLOYEE_BY_ID = "/employees/{id}";
    public static final String FIND_EMPLOYEE_QUERY = "/employees";
    public static final String DELETE_EMPLOYEE = "/employees/{id}";

    // Supplier Endpoints
    public static final String CREATE_SUPPLIER = "/suppliers";
    public static final String FIND_ALL_SUPPLIERS = "/suppliers";
    public static final String FIND_SUPPLIER_BY_ID = "/suppliers/{id}";
    public static final String UPDATE_SUPPLIER = "/suppliers";
    public static final String DELETE_SUPPLIER = "/suppliers/{id}";

    // Product Endpoints
    public static final String CREATE_PRODUCT = "/products";
    public static final String FIND_ALL_PRODUCT = "/products";
    public static final String FIND_PRODUCT_BY_ID = "/products/{id}";
    public static final String UPDATE_PRODUCT = "/products";
    public static final String DELETE_PRODUCT = "/products/{id}";
}