package dev.anirban.stockbot.repo;

import dev.anirban.stockbot.entity.Restock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestockRepo extends JpaRepository<Restock, Integer> {
}