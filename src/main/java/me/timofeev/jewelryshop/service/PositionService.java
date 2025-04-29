package me.timofeev.jewelryshop.service;

import me.timofeev.jewelryshop.entity.Position;
import me.timofeev.jewelryshop.repo.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {
    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<Position> getAll() {
        return positionRepository.findAll();
    }

    public Optional<Position> getById(Long id) {
        return positionRepository.findById(id);
    }

    public Position create(Position position) {
        return positionRepository.save(position);
    }

    public Position update(Long id, Position updatedPosition) {
        Optional<Position> optionalPosition = positionRepository.findById(id);
        optionalPosition.ifPresent(position -> {
            position.setName(updatedPosition.getName());
            positionRepository.save(position);
        });
        return optionalPosition.orElse(null);
    }

    public void delete(Long id) {
        positionRepository.findById(id).ifPresent(positionRepository::delete);
    }
}