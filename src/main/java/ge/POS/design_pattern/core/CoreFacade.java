package ge.POS.design_pattern.core;

import ge.POS.design_pattern.core.DTOs.*;
import ge.POS.design_pattern.core.exceptions.*;
import ge.POS.design_pattern.core.models.Product;
import ge.POS.design_pattern.core.models.Receipt;
import ge.POS.design_pattern.core.models.Unit;
import ge.POS.design_pattern.core.models.Sales;
import ge.POS.design_pattern.core.services.ProductService;
import ge.POS.design_pattern.core.services.ReceiptService;
import ge.POS.design_pattern.core.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoreFacade {

    @Autowired
    private UnitService unitService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ReceiptService receiptService;


    public Unit createUnit(CreateUnitDTO request) throws UnitExistsWithNameException {
        return unitService.createUnit(request.getName());
    }

    public Unit getUnit(String id) throws UnitNotFoundException {
        return unitService.getUnit(id);
    }

    public List<Unit> getAllUnits() {
        return unitService.getAllUnits();
    }

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    public Product createProduct(CreateProductDTO productDTO) throws ProductExistsWithBarcodeException, UnitNotFoundException {
        Unit unit = unitService.getUnit(productDTO.getUnit_id());
        return productService.createProduct(unit, productDTO.getProduct_name(), productDTO.getProduct_barcode(), productDTO.getProduct_price());
    }

    public Product getProduct(String id) throws ProductNotFoundException {
        return productService.getProduct(id);
    }

    public Product updateProduct(String id, UpdateProductPriceDTO productDTO) throws ProductNotFoundException {
        return productService.updateProduct(id, productDTO.getPrice());
    }

    public Receipt createReceipt() {
        return receiptService.createReceipt();
    }

    public Receipt getReceipt(String id) throws ReceiptNotFoundException {
        return receiptService.getReceipt(id);
    }

    public void deleteReceipt(String id) throws ReceiptNotFoundException, ReceiptClosedException {
        receiptService.deleteReceipt(id);
    }

    public void updateReceiptStatus(String id, ReceiptStatusDTO receiptDTO) throws ReceiptNotFoundException {
        receiptService.updateReceiptStatus(id, receiptDTO.isStatus());
    }

    public Receipt addProductToReceipt(String id, ProductForReceiptDTO receiptDTO) throws ProductNotFoundException, ReceiptNotFoundException, ReceiptClosedException {
        Product product = productService.getProduct(receiptDTO.getProductID());
        return receiptService.addProductToReceipt(id, receiptDTO.getQuantity(), product);
    }

    public Sales getSales() {
        List<Receipt> receipts = receiptService.getAllReceipts();
        Sales sales = new Sales();
        return sales.count_revenue(receipts);
    }
}
