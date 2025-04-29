package me.timofeev.jewelryshop.service;

import me.timofeev.jewelryshop.entity.Cheque;
import me.timofeev.jewelryshop.entity.Employee;
import me.timofeev.jewelryshop.entity.Position;
import me.timofeev.jewelryshop.repo.ChequeRepository;
import me.timofeev.jewelryshop.repo.EmployeeRepository;
import me.timofeev.jewelryshop.repo.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final ChequeRepository chequeRepository;

    public EmployeeService(EmployeeRepository employeeRepository, PositionRepository positionRepository, ChequeRepository chequeRepository) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.chequeRepository = chequeRepository;
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Long id, Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        optionalEmployee.ifPresent(employee -> {
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
            employeeRepository.save(employee);
        });
        return optionalEmployee.orElse(null);
    }

    public void delete(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.getPositions().clear();
            employeeRepository.save(employee);

            List<Cheque> cheques = chequeRepository.findAll();
            for (Cheque cheque : cheques) {
                if (cheque.getEmployee() != null && cheque.getEmployee().getId().equals(id)) {
                    chequeRepository.deleteById(cheque.getId());
                }
            }
            employeeRepository.delete(employee);
        }
    }

    public Employee addPositionToEmployee(Long employeeId, Long positionId) {
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