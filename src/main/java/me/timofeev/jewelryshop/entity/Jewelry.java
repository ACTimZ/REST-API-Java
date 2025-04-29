package me.timofeev.jewelryshop.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Jewelry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @ManyToOne
    private Material material;

    private Integer assay;
    private BigDecimal price;
    private String description;

    public Jewelry() {
    }

    public Jewelry(String name, String type, Material material, Integer assay, BigDecimal price, String description) {
        this.name = name;
        this.type = type;
        this.material = material;
        this.assay = assay;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getAssay() {
        return assay;
    }

    public void setAssay(Integer assay) {
        this.assay = assay;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}