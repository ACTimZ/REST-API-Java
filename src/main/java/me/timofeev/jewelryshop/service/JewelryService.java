package me.timofeev.jewelryshop.service;

import me.timofeev.jewelryshop.entity.Cheque;
import me.timofeev.jewelryshop.entity.Jewelry;
import me.timofeev.jewelryshop.repo.ChequeRepository;
import me.timofeev.jewelryshop.repo.JewelryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JewelryService {
    private final JewelryRepository jewelryRepository;
    private final ChequeRepository chequeRepository;

    public JewelryService(JewelryRepository jewelryRepository, ChequeRepository chequeRepository) {
        this.jewelryRepository = jewelryRepository;
        this.chequeRepository = chequeRepository;
    }

    public List<Jewelry> getAll() {
        return jewelryRepository.findAll();
    }

    public Optional<Jewelry> getById(Long id) {
        return jewelryRepository.findById(id);
    }

    public Jewelry create(Jewelry jewelry) {
        return jewelryRepository.save(jewelry);
    }

    public Jewelry update(Long id, Jewelry updatedJewelry) {
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

    public void delete(Long id) {
        Optional<Jewelry> optionalJewelry = jewelryRepository.findById(id);
        if (optionalJewelry.isPresent()) {
            List<Cheque> cheques = chequeRepository.findAll();
            for (Cheque cheque : cheques) {
                if (cheque.getJewelry() != null && cheque.getJewelry().getId().equals(id)) {
                    chequeRepository.deleteById(cheque.getId());
                }
            }
            jewelryRepository.delete(optionalJewelry.get());
        }
    }
}