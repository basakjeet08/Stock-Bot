package dev.anirban.stockbot.controller;


import dev.anirban.stockbot.dto.response.ResponseWrapper;
import dev.anirban.stockbot.entity.Supplier;
import dev.anirban.stockbot.service.SupplierService;
import dev.anirban.stockbot.util.UrlConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService service;

    @PostMapping(UrlConstants.CREATE_SUPPLIER)
    public ResponseWrapper<Supplier> create(@RequestBody Supplier supplier) {
        Supplier data = service.create(supplier);
        return new ResponseWrapper<>(data);
    }

    @GetMapping(UrlConstants.FIND_ALL_SUPPLIERS)
    public ResponseWrapper<List<Supplier>> findAll() {
        List<Supplier> data = service.findAll();
        return new ResponseWrapper<>(data);
    }

    @GetMapping(UrlConstants.FIND_SUPPLIER_BY_ID)
    public ResponseWrapper<Supplier> findById(@PathVariable Integer id) {
        Supplier data = service.findById(id);
        return new ResponseWrapper<>(data);
    }

    @PutMapping(UrlConstants.UPDATE_SUPPLIER)
    public ResponseWrapper<Supplier> update(@RequestBody Supplier supplier) {
        Supplier data = service.update(supplier);
        return new ResponseWrapper<>(data);
    }

    @DeleteMapping(UrlConstants.DELETE_SUPPLIER)
    public ResponseWrapper<Void> deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return new ResponseWrapper<>("Supplier with ID : " + id + " is deleted Successfully");
    }
}