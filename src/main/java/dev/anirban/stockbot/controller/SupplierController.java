package dev.anirban.stockbot.controller;


import dev.anirban.stockbot.dto.common.SupplierDto;
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
    public ResponseWrapper<SupplierDto> create(@RequestBody SupplierDto supplier) {
        SupplierDto data = service.create(supplier).toSupplierDto();
        return new ResponseWrapper<>(data);
    }

    @GetMapping(UrlConstants.FIND_ALL_SUPPLIERS)
    public ResponseWrapper<List<SupplierDto>> findAll() {
        List<SupplierDto> data = service
                .findAll()
                .stream()
                .map(Supplier::toSupplierDto)
                .toList();

        return new ResponseWrapper<>(data);
    }

    @GetMapping(UrlConstants.FIND_SUPPLIER_BY_ID)
    public ResponseWrapper<SupplierDto> findById(@PathVariable Integer id) {
        SupplierDto data = service.findById(id).toSupplierDto();
        return new ResponseWrapper<>(data);
    }

    @PutMapping(UrlConstants.UPDATE_SUPPLIER)
    public ResponseWrapper<SupplierDto> update(@RequestBody SupplierDto supplier) {
        SupplierDto data = service.update(supplier).toSupplierDto();
        return new ResponseWrapper<>(data);
    }

    @DeleteMapping(UrlConstants.DELETE_SUPPLIER)
    public ResponseWrapper<Void> deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return new ResponseWrapper<>("Supplier with ID : " + id + " is deleted Successfully");
    }
}