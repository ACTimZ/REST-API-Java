package me.timofeev.jewelryshop.controller;

import me.timofeev.jewelryshop.entity.Cheque;
import me.timofeev.jewelryshop.repo.ChequeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cheques")
public class ChequeController {
    private final ChequeRepository chequeRepository;

    public ChequeController(ChequeRepository chequeRepository) {
        this.chequeRepository = chequeRepository;
    }

    @GetMapping
    public List<Cheque> getAll() {
        return chequeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cheque getById(@PathVariable Long id) {
        return chequeRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Cheque create(@RequestBody Cheque cheque) {
        return chequeRepository.save(cheque);
    }

    @PutMapping("/{id}")
    public Cheque update(@PathVariable Long id, @RequestBody Cheque updatedCheque) {
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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        chequeRepository.deleteById(id);
    }
}