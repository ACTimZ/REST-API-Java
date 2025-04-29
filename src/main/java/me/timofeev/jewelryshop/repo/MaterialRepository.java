package me.timofeev.jewelryshop.repo;

import me.timofeev.jewelryshop.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}