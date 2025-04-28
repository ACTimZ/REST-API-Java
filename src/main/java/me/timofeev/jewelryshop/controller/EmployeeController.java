package me.timofeev.jewelryshop.controller;

import me.timofeev.jewelryshop.entity.Cheque;
import me.timofeev.jewelryshop.entity.Employee;
import me.timofeev.jewelryshop.entity.Position;
import me.timofeev.jewelryshop.repo.EmployeeRepository;
import me.timofeev.jewelryshop.repo.PositionRepository;
import me.timofeev.jewelryshop.repo.ChequeRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final ChequeRepository chequeRepository;

    public EmployeeController(EmployeeRepository employeeRepository, PositionRepository positionRepository,
            ChequeRepository chequeRepository) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.chequeRepository = chequeRepository;
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setFirstName(updatedEmployee.getFirstName());
                    employee.setLastName(updatedEmployee.getLastName());

                    if (employee.getContactInfo() != null && updatedEmployee.getContactInfo() != null) {
                        employee.getContactInfo().setPhone(updatedEmployee.getContactInfo().getPhone());
                        employee.getContactInfo().setEmail(updatedEmployee.getContactInfo().getEmail());
                        employee.getContactInfo().setAddress(updatedEmployee.getContactInfo().getAddress());
                    } else if (updatedEmployee.getContactInfo() != null) {
                        employee.setContactInfo(updatedEmployee.getContactInfo());
                    }

                    employee.setPositions(updatedEmployee.getPositions());
                    return employeeRepository.save(employee);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeRepository.findById(id).ifPresent(employee -> {
            employee.getPositions().clear();
            employeeRepository.save(employee);

            List<Cheque> cheques = chequeRepository.findAll();
            for (Cheque cheque : cheques) {
                if (cheque.getEmployee() != null && cheque.getEmployee().getId().equals(id)) {
                    chequeRepository.deleteById(cheque.getId());
                }
            }
            
            employeeRepository.deleteById(id);
        });
    }

    @PostMapping("/{employeeId}/addPosition/{positionId}")
    public Employee addPositionToEmployee(@PathVariable Long employeeId, @PathVariable Long positionId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        Optional<Position> positionOpt = positionRepository.findById(positionId);

        if (employeeOpt.isPresent() && positionOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            Position position = positionOpt.get();
            employee.getPositions().add(position);
            return employeeRepository.save(employee);
        }
        return null;
    }
}