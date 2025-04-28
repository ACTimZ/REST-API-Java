package me.timofeev.jewelryshop.repo;

import me.timofeev.jewelryshop.entity.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {
}