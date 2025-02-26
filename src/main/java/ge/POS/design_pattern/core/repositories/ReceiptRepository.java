package ge.POS.design_pattern.core.repositories;

import ge.POS.design_pattern.core.models.ProductInReceipt;
import ge.POS.design_pattern.core.models.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, String> {
//    void addProduct(ProductInReceipt product);
}
