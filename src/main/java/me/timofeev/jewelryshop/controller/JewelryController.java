package me.timofeev.jewelryshop.controller;

import me.timofeev.jewelryshop.entity.Jewelry;
import me.timofeev.jewelryshop.repo.JewelryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewelry")
public class JewelryController {
    private final JewelryRepository jewelryRepository;

    public JewelryController(JewelryRepository jewelryRepository) {
        this.jewelryRepository = jewelryRepository;
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
        return jewelryRepository.findById(id)
                .map(jewelry -> {
                    jewelry.setName(updatedJewelry.getName());
                    jewelry.setType(updatedJewelry.getType());
                    jewelry.setMaterial(updatedJewelry.getMaterial());
                    jewelry.setAssay(updatedJewelry.getAssay());
                    jewelry.setPrice(updatedJewelry.getPrice());
                    jewelry.setDescription(updatedJewelry.getDescription());
                    return jewelryRepository.save(jewelry);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        jewelryRepository.deleteById(id);
    }
}