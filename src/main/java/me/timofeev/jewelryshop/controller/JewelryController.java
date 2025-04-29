package me.timofeev.jewelryshop.controller;

import me.timofeev.jewelryshop.entity.Jewelry;
import me.timofeev.jewelryshop.service.JewelryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jewelry")
public class JewelryController {
    private final JewelryService jewelryService;

    public JewelryController(JewelryService jewelryService) {
        this.jewelryService = jewelryService;
    }

    @GetMapping
    public List<Jewelry> getAll() {
        return jewelryService.getAll();
    }

    @GetMapping("/{id}")
    public Jewelry getById(@PathVariable Long id) {
        return jewelryService.getById(id).orElse(null);
    }

    @PostMapping
    public Jewelry create(@RequestBody Jewelry jewelry) {
        return jewelryService.create(jewelry);
    }

    @PutMapping("/{id}")
    public Jewelry update(@PathVariable Long id, @RequestBody Jewelry updatedJewelry) {
        return jewelryService.update(id, updatedJewelry);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        jewelryService.delete(id);
    }
}