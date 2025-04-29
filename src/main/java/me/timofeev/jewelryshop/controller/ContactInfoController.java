package me.timofeev.jewelryshop.controller;

import me.timofeev.jewelryshop.entity.ContactInfo;
import me.timofeev.jewelryshop.service.ContactInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactinfo")
public class ContactInfoController {
    private final ContactInfoService contactInfoService;

    public ContactInfoController(ContactInfoService contactInfoService) {
        this.contactInfoService = contactInfoService;
    }

    @GetMapping
    public List<ContactInfo> getAll() {
        return contactInfoService.getAll();
    }

    @GetMapping("/{id}")
    public ContactInfo getById(@PathVariable Long id) {
        return contactInfoService.getById(id).orElse(null);
    }

    @PostMapping
    public ContactInfo create(@RequestBody ContactInfo contactInfo) {
        return contactInfoService.create(contactInfo);
    }

    @PutMapping("/{id}")
    public ContactInfo update(@PathVariable Long id, @RequestBody ContactInfo updatedContactInfo) {
        return contactInfoService.update(id, updatedContactInfo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contactInfoService.delete(id);
    }
}