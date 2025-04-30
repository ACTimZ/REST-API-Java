package me.timofeev.jewelryshop.service;

import me.timofeev.jewelryshop.dto.ChequeCreateDTO;
import me.timofeev.jewelryshop.dto.ChequeItemDTO;
import me.timofeev.jewelryshop.entity.Cheque;
import me.timofeev.jewelryshop.entity.ChequeItem;
import me.timofeev.jewelryshop.repo.ChequeRepository;
import me.timofeev.jewelryshop.repo.ClientRepository;
import me.timofeev.jewelryshop.repo.EmployeeRepository;
import me.timofeev.jewelryshop.repo.JewelryRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChequeService {
    private final ChequeRepository chequeRepository;
    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;
    private final JewelryRepository jewelryRepository;

    public ChequeService(ChequeRepository chequeRepository, EmployeeRepository employeeRepository,
            ClientRepository clientRepository, JewelryRepository jewelryRepository) {
        this.jewelryRepository = jewelryRepository;
        this.chequeRepository = chequeRepository;
        this.employeeRepository = employeeRepository;
        this.clientRepository = clientRepository;
    }

    public List<Cheque> getAll() {
        return chequeRepository.findAll();
    }

    public Optional<Cheque> getById(Long id) {
        return chequeRepository.findById(id);
    }

    public Cheque create(ChequeCreateDTO chequeCreateDTO) {
        Cheque cheque = new Cheque();

        cheque.setEmployee(employeeRepository.findById(chequeCreateDTO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found")));
        cheque.setClient(clientRepository.findById(chequeCreateDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found")));

        cheque.setPurchaseDate(chequeCreateDTO.getPurchaseDate());

        List<ChequeItem> items = new ArrayList<>();
        for (ChequeItemDTO itemDTO : chequeCreateDTO.getItems()) {
            ChequeItem item = new ChequeItem();

            item.setJewelry(jewelryRepository.findById(itemDTO.getJewelryId())
                    .orElseThrow(() -> new RuntimeException("Jewelry not found")));

            item.setQuantity(itemDTO.getQuantity());
            item.setCheque(cheque);
            items.add(item);
        }

        cheque.setItems(items);

        return chequeRepository.save(cheque);
    }

    public Cheque update(Long id, Cheque updatedCheque) {
        Optional<Cheque> optionalCheque = chequeRepository.findById(id);
        if (optionalCheque.isPresent()) {
            Cheque cheque = optionalCheque.get();

            cheque.setEmployee(updatedCheque.getEmployee());
            cheque.setClient(updatedCheque.getClient());
            cheque.setPurchaseDate(updatedCheque.getPurchaseDate());

            cheque.getItems().clear();
            for (ChequeItem updatedItem : updatedCheque.getItems()) {
                updatedItem.setCheque(cheque);
                cheque.getItems().add(updatedItem);
            }
            return chequeRepository.save(cheque);
        }
        return null;
    }

    public void delete(Long id) {
        chequeRepository.findById(id).ifPresent(chequeRepository::delete);
    }
}