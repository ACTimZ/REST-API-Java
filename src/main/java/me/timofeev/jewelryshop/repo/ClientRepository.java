package me.timofeev.jewelryshop.repo;

import me.timofeev.jewelryshop.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}