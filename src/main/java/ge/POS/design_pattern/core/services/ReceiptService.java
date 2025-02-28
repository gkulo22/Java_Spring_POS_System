package ge.POS.design_pattern.core.services;

import ge.POS.design_pattern.core.exceptions.ReceiptNotFoundException;
import ge.POS.design_pattern.core.models.Product;
import ge.POS.design_pattern.core.models.ProductInReceipt;
import ge.POS.design_pattern.core.models.Receipt;
import ge.POS.design_pattern.core.repositories.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public Receipt createReceipt() {
        Receipt receipt = new Receipt();
        receiptRepository.save(receipt);
        return receipt;
    }

    public Receipt getReceipt(String id) throws ReceiptNotFoundException {
        return receiptRepository.findById(id).orElseThrow(() -> new ReceiptNotFoundException(id));
    }

    public void deleteReceipt(String id) throws ReceiptNotFoundException {
        if (!receiptRepository.existsById(id)) {
            throw new ReceiptNotFoundException(id);
        }
        receiptRepository.deleteById(id);
    }

    public void updateReceiptStatus(String id, boolean status) throws ReceiptNotFoundException {
        Receipt receipt = receiptRepository.findById(id).orElseThrow(() -> new ReceiptNotFoundException(id));
        receipt.setStatus(status);
        receiptRepository.save(receipt);
    }

    public Receipt addProductToReceipt(String id, int quantity, Product product) throws ReceiptNotFoundException {
        Receipt receipt = receiptRepository.findById(id).orElseThrow(() -> new ReceiptNotFoundException(id));
        ProductInReceipt productInReceipt = new ProductInReceipt();
        productInReceipt.setId(product.getId());
        productInReceipt.setPrice(product.getPrice());
        productInReceipt.setQuantity(quantity);
        productInReceipt.setReceipt(receipt);
        receipt.addProduct(productInReceipt);
        receiptRepository.save(receipt);
        return receipt;
    }
}
