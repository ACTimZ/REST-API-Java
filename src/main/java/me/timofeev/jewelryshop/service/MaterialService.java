package me.timofeev.jewelryshop.service;

import me.timofeev.jewelryshop.entity.Material;
import me.timofeev.jewelryshop.repo.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<Material> getAll() {
        return materialRepository.findAll();
    }

    public Optional<Material> getById(Long id) {
        return materialRepository.findById(id);
    }

    public Material create(Material material) {
        return materialRepository.save(material);
    }

    public Material update(Long id, Material updatedMaterial) {
        Optional<Material> optionalMaterial = materialRepository.findById(id);
        optionalMaterial.ifPresent(material -> {
            material.setName(updatedMaterial.getName());
            materialRepository.save(material);
        });
        return optionalMaterial.orElse(null);
    }

    public void delete(Long id) {
        materialRepository.findById(id).ifPresent(materialRepository::delete);
    }
}