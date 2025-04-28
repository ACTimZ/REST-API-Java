package me.timofeev.jewelryshop.repo;

import me.timofeev.jewelryshop.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}