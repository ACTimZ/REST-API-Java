package me.timofeev.jewelryshop.repo;

import me.timofeev.jewelryshop.entity.Jewelry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JewelryRepository extends JpaRepository<Jewelry, Long> {
}