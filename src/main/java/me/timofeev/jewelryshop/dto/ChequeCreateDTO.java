package me.timofeev.jewelryshop.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ChequeCreateDTO {
    private Long employeeId;
    private Long clientId;
    private LocalDateTime purchaseDate;
    private List<ChequeItemDTO> items;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<ChequeItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ChequeItemDTO> items) {
        this.items = items;
    }
}