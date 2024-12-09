package dev.anirban.stockbot.service;

import dev.anirban.stockbot.dto.common.ProductDto;
import dev.anirban.stockbot.entity.Product;
import dev.anirban.stockbot.entity.Supplier;
import dev.anirban.stockbot.exception.ProductNotFound;
import dev.anirban.stockbot.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
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
                .restockList(new HashSet<>())
                .build();

        supplier.addProduct(newProduct);

        return productRepo.save(newProduct);
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }

    public Product findById(Integer id) {
        return productRepo
                .findById(id)
                .orElseThrow(() -> new ProductNotFound(id));
    }

    public Product update(ProductDto productDto) {
        Product savedProduct = findById(productDto.getId());

        if (productDto.getName() != null)
            savedProduct.setName(productDto.getName());
        if (productDto.getCategory() != null)
            savedProduct.setCategory(productDto.getCategory());
        if (productDto.getDescription() != null)
            savedProduct.setDescription(productDto.getDescription());
        if (productDto.getPrice() != null)
            savedProduct.setPrice(productDto.getPrice());
        if (productDto.getQuantity() != null)
            savedProduct.setQuantity(productDto.getQuantity());
        if (productDto.getRestockThreshold() != null)
            savedProduct.setRestockThreshold(productDto.getRestockThreshold());
        if (productDto.getHoldingCapacity() != null)
            savedProduct.setHoldingCapacity(productDto.getHoldingCapacity());

        if (productDto.getSuppliedById() != null) {
            Supplier newSupplier = supplierService.findById(productDto.getSuppliedById());
            Supplier oldSupplier = supplierService.findById(savedProduct.getSuppliedBy().getId());

            oldSupplier.removeProduct(savedProduct);
            newSupplier.addProduct(savedProduct);
        }

        savedProduct.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        return productRepo.save(savedProduct);
    }

    public void deleteById(Integer id) {
        if (!productRepo.existsById(id))
            throw new ProductNotFound(id);

        productRepo.deleteById(id);
    }
}