package me.timofeev.jewelryshop.controller;

import me.timofeev.jewelryshop.entity.Position;
import me.timofeev.jewelryshop.service.PositionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/positions")
public class PositionController {
    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public List<Position> getAll() {
        return positionService.getAll();
    }

    @GetMapping("/{id}")
    public Position getById(@PathVariable Long id) {
        return positionService.getById(id).orElse(null);
    }

    @PostMapping
    public Position create(@RequestBody Position position) {
        return positionService.create(position);
    }

    @PutMapping("/{id}")
    public Position update(@PathVariable Long id, @RequestBody Position updatedPosition) {
        return positionService.update(id, updatedPosition);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        positionService.delete(id);
    }
}