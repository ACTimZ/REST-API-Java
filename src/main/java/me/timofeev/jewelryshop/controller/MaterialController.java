package me.timofeev.jewelryshop.controller;

import me.timofeev.jewelryshop.entity.Material;
import me.timofeev.jewelryshop.service.MaterialService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materials")
public class MaterialController {
    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public List<Material> getAll() {
        return materialService.getAll();
    }

    @GetMapping("/{id}")
    public Material getById(@PathVariable Long id) {
        return materialService.getById(id).orElse(null);
    }

    @PostMapping
    public Material create(@RequestBody Material material) {
        return materialService.create(material);
    }

    @PutMapping("/{id}")
    public Material update(@PathVariable Long id, @RequestBody Material updatedMaterial) {
        return materialService.update(id, updatedMaterial);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        materialService.delete(id);
    }
}