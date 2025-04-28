package me.timofeev.jewelryshop.repo;

import me.timofeev.jewelryshop.entity.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChequeRepository extends JpaRepository<Cheque, Long> {
}