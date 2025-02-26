package ge.POS.design_pattern.core.models;

import jakarta.persistence.*;

@Entity
public class ProductInReceipt extends Product {
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "receipt_id", nullable = false)
    private Receipt receipt;

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    @Transient
    public double getTotal() {
        return super.getPrice() * this.quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}