package me.timofeev.jewelryshop.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

@Entity
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Client client;

    @ManyToMany
    @JoinTable(name = "cheque_jewelry", joinColumns = @JoinColumn(name = "cheque_id"), inverseJoinColumns = @JoinColumn(name = "jewelry_id"))
    private List<Jewelry> jewelryList = new ArrayList<>();

    private LocalDateTime purchaseDate;

    public Cheque() {
    }

    public Cheque(Employee employee, Client client, List<Jewelry> jewelryList, LocalDateTime purchaseDate) {
        this.employee = employee;
        this.client = client;
        this.jewelryList = jewelryList;
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

    public List<Jewelry> getJewelry() {
        return jewelryList;
    }

    public void setJewelry(List<Jewelry> jewelryList) {
        this.jewelryList = jewelryList;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}