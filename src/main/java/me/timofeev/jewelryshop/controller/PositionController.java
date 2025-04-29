package me.timofeev.jewelryshop.controller;

import me.timofeev.jewelryshop.entity.Position;
import me.timofeev.jewelryshop.repo.PositionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/positions")
public class PositionController {
    private final PositionRepository positionRepository;

    public PositionController(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @GetMapping
    public List<Position> getAll() {
        return positionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Position getById(@PathVariable Long id) {
        return positionRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Position create(@RequestBody Position position) {
        return positionRepository.save(position);
    }

    @PutMapping("/{id}")
    public Position update(@PathVariable Long id, @RequestBody Position updatedPosition) {
        Optional<Position> optionalPosition = positionRepository.findById(id);

        optionalPosition.ifPresent(position -> {
            position.setName(updatedPosition.getName());
            positionRepository.save(position);
        });

        return optionalPosition.orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        positionRepository.deleteById(id);
    }
}