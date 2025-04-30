package me.timofeev.jewelryshop.dto;

public class ChequeItemDTO {
    private Long jewelryId;
    private int quantity;

    public Long getJewelryId() {
        return jewelryId;
    }

    public void setJewelryId(Long jewelryId) {
        this.jewelryId = jewelryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}