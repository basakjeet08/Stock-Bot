package dev.anirban.stockbot.service;


import dev.anirban.stockbot.dto.common.SupplierDto;
import dev.anirban.stockbot.entity.Supplier;
import dev.anirban.stockbot.exception.SupplierNotFound;
import dev.anirban.stockbot.repo.SupplierRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepo supplierRepo;

    public Supplier create(SupplierDto supplier) {

        Supplier newSupplier = Supplier
                .builder()
                .name(supplier.getName())
                .address(supplier.getAddress())
                .contactNo(supplier.getContactNo())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .productList(new HashSet<>())
                .restockList(new HashSet<>())
                .build();

        return supplierRepo.save(newSupplier);
    }


    public List<Supplier> findAll() {
        return supplierRepo.findAll();
    }

    public Supplier findById(Integer id) {
        return supplierRepo
                .findById(id)
                .orElseThrow(() -> new SupplierNotFound(id));
    }

    public Supplier update(SupplierDto supplier) {

        Supplier savedSupplier = findById(supplier.getId());

        if (supplier.getName() != null)
            savedSupplier.setName(supplier.getName());

        if (supplier.getAddress() != null)
            savedSupplier.setAddress(supplier.getAddress());

        if (supplier.getContactNo() != null)
            savedSupplier.setContactNo(supplier.getContactNo());

        savedSupplier.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        return supplierRepo.save(savedSupplier);
    }

    public void deleteById(Integer id) {
        if (!supplierRepo.existsById(id))
            throw new SupplierNotFound(id);

        supplierRepo.deleteById(id);
    }
}