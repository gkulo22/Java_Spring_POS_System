package ge.POS.design_pattern.core.repositories;

import ge.POS.design_pattern.core.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM Product u WHERE u.barcode = :barcode")
    boolean hasBarcode(String barcode);
}
