package me.timofeev.jewelryshop.controller;

import me.timofeev.jewelryshop.entity.Cheque;
import me.timofeev.jewelryshop.entity.Jewelry;
import me.timofeev.jewelryshop.repo.JewelryRepository;
import me.timofeev.jewelryshop.repo.ChequeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jewelry")
public class JewelryController {
    private final JewelryRepository jewelryRepository;
    private final ChequeRepository chequeRepository;

    public JewelryController(JewelryRepository jewelryRepository, ChequeRepository chequeRepository) {
        this.jewelryRepository = jewelryRepository;
        this.chequeRepository = chequeRepository;
    }

    @GetMapping
    public List<Jewelry> getAll() {
        return jewelryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Jewelry getById(@PathVariable Long id) {
        return jewelryRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Jewelry create(@RequestBody Jewelry jewelry) {
        return jewelryRepository.save(jewelry);
    }

    @PutMapping("/{id}")
    public Jewelry update(@PathVariable Long id, @RequestBody Jewelry updatedJewelry) {
        Optional<Jewelry> optionalJewelry = jewelryRepository.findById(id);

        optionalJewelry.ifPresent(jewelry -> {
            jewelry.setName(updatedJewelry.getName());
            jewelry.setType(updatedJewelry.getType());
            jewelry.setMaterial(updatedJewelry.getMaterial());
            jewelry.setAssay(updatedJewelry.getAssay());
            jewelry.setPrice(updatedJewelry.getPrice());
            jewelry.setDescription(updatedJewelry.getDescription());
            jewelryRepository.save(jewelry);
        });

        return optionalJewelry.orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        jewelryRepository.findById(id).ifPresent(jewelry -> {
            List<Cheque> cheques = chequeRepository.findAll();
            for (Cheque cheque : cheques) {
                if (cheque.getJewelry() != null && cheque.getJewelry().getId().equals(id)) {
                    chequeRepository.deleteById(cheque.getId());
                }
            }
            jewelryRepository.deleteById(id);
        });
    }
}