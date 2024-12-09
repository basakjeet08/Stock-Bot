package dev.anirban.stockbot.repo;

import dev.anirban.stockbot.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepo extends JpaRepository<Supplier, Integer> {
}