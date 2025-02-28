package ge.POS.design_pattern.core.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "status", nullable = false)
    private boolean status = true;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ProductInReceipt> products = new ArrayList<>();

    public List<ProductInReceipt> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInReceipt> products) {
        this.products = products;
    }

    public void addProduct(ProductInReceipt product) {
        products.add(product);
    }

    @Transient
    public double getTotal() {
        return products.stream()
                .mapToDouble(ProductInReceipt::getTotal)
                .sum();
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}