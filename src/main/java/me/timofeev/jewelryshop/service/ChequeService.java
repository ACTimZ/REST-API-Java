package me.timofeev.jewelryshop.service;

import me.timofeev.jewelryshop.entity.Cheque;
import me.timofeev.jewelryshop.repo.ChequeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChequeService {
    private final ChequeRepository chequeRepository;

    public ChequeService(ChequeRepository chequeRepository) {
        this.chequeRepository = chequeRepository;
    }

    public List<Cheque> getAll() {
        return chequeRepository.findAll();
    }

    public Optional<Cheque> getById(Long id) {
        return chequeRepository.findById(id);
    }

    public Cheque create(Cheque cheque) {
        return chequeRepository.save(cheque);
    }

    public Cheque update(Long id, Cheque updatedCheque) {
        Optional<Cheque> optionalCheque = chequeRepository.findById(id);
        optionalCheque.ifPresent(cheque -> {
            cheque.setEmployee(updatedCheque.getEmployee());
            cheque.setClient(updatedCheque.getClient());
            cheque.setJewelry(updatedCheque.getJewelry());
            cheque.setPurchaseDate(updatedCheque.getPurchaseDate());
            chequeRepository.save(cheque);
        });
        return optionalCheque.orElse(null);
    }

    public void delete(Long id) {
        chequeRepository.findById(id).ifPresent(chequeRepository::delete);
    }
}