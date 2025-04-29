package me.timofeev.jewelryshop.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Client client;

    @OneToOne
    private Jewelry jewelry;

    private LocalDateTime purchaseDate;

    public Cheque() {
    }

    public Cheque(Employee employee, Client client, Jewelry jewelry, LocalDateTime purchaseDate) {
        this.employee = employee;
        this.client = client;
        this.jewelry = jewelry;
        this.purchaseDate = purchaseDate;
    }

    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Jewelry getJewelry() {
        return jewelry;
    }

    public void setJewelry(Jewelry jewelry) {
        this.jewelry = jewelry;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}