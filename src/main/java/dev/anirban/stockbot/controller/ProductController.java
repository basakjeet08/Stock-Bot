package dev.anirban.stockbot.controller;


import dev.anirban.stockbot.dto.common.ProductDto;
import dev.anirban.stockbot.dto.response.ResponseWrapper;
import dev.anirban.stockbot.entity.Product;
import dev.anirban.stockbot.service.ProductService;
import dev.anirban.stockbot.util.UrlConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}