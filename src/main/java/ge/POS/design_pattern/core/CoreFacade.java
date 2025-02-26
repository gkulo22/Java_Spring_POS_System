package ge.POS.design_pattern.core;

import ge.POS.design_pattern.core.DTOs.CreateProductDTO;
import ge.POS.design_pattern.core.DTOs.CreateUnitDTO;
import ge.POS.design_pattern.core.DTOs.UpdateProductPriceDTO;
import ge.POS.design_pattern.core.exceptions.ProductExistsWithBarcodeException;
import ge.POS.design_pattern.core.exceptions.ProductNotFoundException;
import ge.POS.design_pattern.core.exceptions.UnitExistsWithNameException;
import ge.POS.design_pattern.core.exceptions.UnitNotFoundException;
import ge.POS.design_pattern.core.models.Product;
import ge.POS.design_pattern.core.models.Unit;
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
}
