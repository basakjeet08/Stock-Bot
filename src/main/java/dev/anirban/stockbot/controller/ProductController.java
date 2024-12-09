package dev.anirban.stockbot.controller;


import dev.anirban.stockbot.dto.common.ProductDto;
import dev.anirban.stockbot.dto.response.ResponseWrapper;
import dev.anirban.stockbot.entity.Product;
import dev.anirban.stockbot.service.ProductService;
import dev.anirban.stockbot.util.UrlConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping(UrlConstants.CREATE_PRODUCT)
    public ResponseWrapper<ProductDto> create(@RequestBody ProductDto productDto) {
        ProductDto data = service.create(productDto).toProductDto();
        return new ResponseWrapper<>(data);
    }

    @GetMapping(UrlConstants.FIND_ALL_PRODUCT)
    public ResponseWrapper<List<ProductDto>> findAll() {
        List<ProductDto> data = service
                .findAll()
                .stream()
                .map(Product::toProductDto)
                .toList();

        return new ResponseWrapper<>(data);
    }

    @GetMapping(UrlConstants.FIND_PRODUCT_BY_ID)
    public ResponseWrapper<ProductDto> findById(@PathVariable Integer id) {
        ProductDto data = service.findById(id).toProductDto();
        return new ResponseWrapper<>(data);
    }

    @PutMapping(UrlConstants.UPDATE_PRODUCT)
    public ResponseWrapper<ProductDto> update(@RequestBody ProductDto productDto) {
        ProductDto data = service.update(productDto).toProductDto();
        return new ResponseWrapper<>(data);
    }

    @DeleteMapping(UrlConstants.DELETE_PRODUCT)
    public ResponseWrapper<Void> deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return new ResponseWrapper<>("Product with ID : " + id + " is deleted successfully");
    }
}