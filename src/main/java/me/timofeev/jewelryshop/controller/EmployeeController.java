package me.timofeev.jewelryshop.controller;

import me.timofeev.jewelryshop.entity.Employee;
import me.timofeev.jewelryshop.service.EmployeeService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeService.getById(id).orElse(null);
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return employeeService.update(id, updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }

    @PostMapping("/{employeeId}/addPosition/{positionId}")
    public Employee addPositionToEmployee(@PathVariable Long employeeId, @PathVariable Long positionId) {
        return employeeService.addPositionToEmployee(employeeId, positionId);
    }
}