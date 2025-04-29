package me.timofeev.jewelryshop.controller;

import me.timofeev.jewelryshop.entity.Cheque;
import me.timofeev.jewelryshop.service.ChequeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cheques")
public class ChequeController {
    private final ChequeService chequeService;

    public ChequeController(ChequeService chequeService) {
        this.chequeService = chequeService;
    }

    @GetMapping
    public List<Cheque> getAll() {
        return chequeService.getAll();
    }

    @GetMapping("/{id}")
    public Cheque getById(@PathVariable Long id) {
        return chequeService.getById(id).orElse(null);
    }

    @PostMapping
    public Cheque create(@RequestBody Cheque cheque) {
        return chequeService.create(cheque);
    }

    @PutMapping("/{id}")
    public Cheque update(@PathVariable Long id, @RequestBody Cheque updatedCheque) {
        return chequeService.update(id, updatedCheque);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        chequeService.delete(id);
    }
}