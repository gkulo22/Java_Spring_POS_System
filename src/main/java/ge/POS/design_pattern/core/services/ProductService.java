package ge.POS.design_pattern.core.services;

import ge.POS.design_pattern.core.DTOs.CreateProductDTO;
import ge.POS.design_pattern.core.DTOs.UpdateProductPriceDTO;
import ge.POS.design_pattern.core.exceptions.ProductExistsWithBarcodeException;
import ge.POS.design_pattern.core.exceptions.ProductNotFoundException;
import ge.POS.design_pattern.core.models.Unit;
import ge.POS.design_pattern.core.repositories.ProductRepository;
import ge.POS.design_pattern.core.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Unit unit, String name, String barcode, double price) throws ProductExistsWithBarcodeException {
        if (productRepository.hasBarcode(barcode)) {
            throw new ProductExistsWithBarcodeException(barcode);
        }
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setBarcode(barcode);
        product.setUnit(unit);

        return productRepository.save(product);
    }

    public Product getProduct(String id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product updateProduct(String id, double price) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ProductNotFoundException(id);
        }
        product.get().setPrice(price);
        return productRepository.save(product.get());
    }

}
