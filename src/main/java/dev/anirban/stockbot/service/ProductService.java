package dev.anirban.stockbot.service;

import dev.anirban.stockbot.dto.common.ProductDto;
import dev.anirban.stockbot.entity.Product;
import dev.anirban.stockbot.entity.Supplier;
import dev.anirban.stockbot.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final SupplierService supplierService;

    public Product create(ProductDto productDto) {
        Supplier supplier = supplierService.findById(productDto.getSuppliedById());

        Product newProduct = Product
                .builder()
                .name(productDto.getName())
                .category(productDto.getCategory())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .restockThreshold(productDto.getRestockThreshold())
                .holdingCapacity(productDto.getHoldingCapacity())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        supplier.addProduct(newProduct);

        return productRepo.save(newProduct);
    }


    public List<Product> findAll() {
        return productRepo.findAll();
    }
}