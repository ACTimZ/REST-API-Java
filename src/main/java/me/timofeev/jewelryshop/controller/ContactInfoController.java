package me.timofeev.jewelryshop.controller;

import me.timofeev.jewelryshop.entity.ContactInfo;
import me.timofeev.jewelryshop.repo.ContactInfoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contactinfo")
public class ContactInfoController {
    private final ContactInfoRepository contactInfoRepository;

    public ContactInfoController(ContactInfoRepository contactInfoRepository) {
        this.contactInfoRepository = contactInfoRepository;
    }

    @GetMapping
    public List<ContactInfo> getAll() {
        return contactInfoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ContactInfo getById(@PathVariable Long id) {
        return contactInfoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public ContactInfo create(@RequestBody ContactInfo contactInfo) {
        return contactInfoRepository.save(contactInfo);
    }

    @PutMapping("/{id}")
    public ContactInfo update(@PathVariable Long id, @RequestBody ContactInfo updatedContactInfo) {
        Optional<ContactInfo> optionalContactInfo = contactInfoRepository.findById(id);

        optionalContactInfo.ifPresent(contactInfo -> {
            contactInfo.setPhone(updatedContactInfo.getPhone());
            contactInfo.setEmail(updatedContactInfo.getEmail());
            contactInfo.setAddress(updatedContactInfo.getAddress());
            contactInfoRepository.save(contactInfo);
        });

        return optionalContactInfo.orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contactInfoRepository.deleteById(id);
    }
}