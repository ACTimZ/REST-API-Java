package me.timofeev.jewelryshop.repo;

import me.timofeev.jewelryshop.entity.ChequeItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChequeItemRepository extends JpaRepository<ChequeItem, Long> {
}