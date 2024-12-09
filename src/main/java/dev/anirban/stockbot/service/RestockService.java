package dev.anirban.stockbot.service;

import dev.anirban.stockbot.dto.common.RestockDto;
import dev.anirban.stockbot.entity.Employee;
import dev.anirban.stockbot.entity.Product;
import dev.anirban.stockbot.entity.Restock;
import dev.anirban.stockbot.entity.Supplier;
import dev.anirban.stockbot.repo.RestockRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RestockService {

    private final RestockRepo restockRepo;
    private final EmployeeService employeeService;
    private final ProductService productService;
    private final SupplierService supplierService;

    public Restock create(Employee requester, RestockDto restockDto) {

        Employee employee = employeeService.findById(requester.getId());
        Product savedProduct = productService.findById(restockDto.getProductRequested());
        Supplier savedSupplier = supplierService.findById(restockDto.getRequestedTo());

        Restock newRestock = Restock
                .builder()
                .quantityOrdered(restockDto.getQuantityOrdered())
                .orderedDate(Timestamp.valueOf(LocalDateTime.now()))
                .deliveredDate(null)
                .cost(restockDto.getCost())
                .status(Restock.Status.REQUESTED)
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        employee.addRestock(newRestock);
        savedSupplier.addRestock(newRestock);
        savedProduct.addRestock(newRestock);

        return restockRepo.save(newRestock);
    }

    public List<Restock> findAll() {
        return restockRepo.findAll();
    }
}