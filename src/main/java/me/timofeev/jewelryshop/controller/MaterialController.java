package me.timofeev.jewelryshop.controller;

import me.timofeev.jewelryshop.entity.Material;
import me.timofeev.jewelryshop.repo.MaterialRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materials")
public class MaterialController {
    private final MaterialRepository materialRepository;

    public MaterialController(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @GetMapping
    public List<Material> getAll() {
        return materialRepository.findAll();
    }

    @GetMapping("/{id}")
    public Material getById(@PathVariable Long id) {
        return materialRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Material create(@RequestBody Material material) {
        return materialRepository.save(material);
    }

    @PutMapping("/{id}")
    public Material update(@PathVariable Long id, @RequestBody Material updatedMaterial) {
        Optional<Material> optionalMaterial = materialRepository.findById(id);
        optionalMaterial.ifPresent(material -> {
            material.setName(updatedMaterial.getName());
            materialRepository.save(material);
        });
        return optionalMaterial.orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        materialRepository.deleteById(id);
    }
}