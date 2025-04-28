package me.timofeev.jewelryshop.repo;

import me.timofeev.jewelryshop.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}