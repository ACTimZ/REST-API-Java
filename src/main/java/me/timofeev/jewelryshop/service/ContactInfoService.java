package me.timofeev.jewelryshop.service;

import me.timofeev.jewelryshop.entity.ContactInfo;
import me.timofeev.jewelryshop.repo.ContactInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactInfoService {
    private final ContactInfoRepository contactInfoRepository;

    public ContactInfoService(ContactInfoRepository contactInfoRepository) {
        this.contactInfoRepository = contactInfoRepository;
    }

    public List<ContactInfo> getAll() {
        return contactInfoRepository.findAll();
    }

    public Optional<ContactInfo> getById(Long id) {
        return contactInfoRepository.findById(id);
    }

    public ContactInfo create(ContactInfo contactInfo) {
        return contactInfoRepository.save(contactInfo);
    }

    public ContactInfo update(Long id, ContactInfo updatedContactInfo) {
        Optional<ContactInfo> optionalContactInfo = contactInfoRepository.findById(id);
        optionalContactInfo.ifPresent(contactInfo -> {
            contactInfo.setPhone(updatedContactInfo.getPhone());
            contactInfo.setEmail(updatedContactInfo.getEmail());
            contactInfo.setAddress(updatedContactInfo.getAddress());
            contactInfoRepository.save(contactInfo);
        });
        return optionalContactInfo.orElse(null);
    }

    public void delete(Long id) {
        contactInfoRepository.findById(id).ifPresent(contactInfoRepository::delete);
    }
}