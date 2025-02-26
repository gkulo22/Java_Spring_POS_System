package ge.POS.design_pattern.infra.api;

import ge.POS.design_pattern.core.CoreFacade;
import ge.POS.design_pattern.core.DTOs.CreateProductDTO;
import ge.POS.design_pattern.core.DTOs.UpdateProductPriceDTO;
import ge.POS.design_pattern.core.exceptions.ProductExistsWithBarcodeException;
import ge.POS.design_pattern.core.exceptions.ProductNotFoundException;
import ge.POS.design_pattern.core.exceptions.UnitNotFoundException;
import ge.POS.design_pattern.core.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private CoreFacade core;


    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductDTO productDTO) {
        try {
            Product product = core.createProduct(productDTO);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (ProductExistsWithBarcodeException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (UnitNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(core.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<Product> getProduct(@PathVariable("product_id") String product_id) {
        try {
            Product product = core.getProduct(product_id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{product_id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("product_id") String product_id,
                                                 @RequestBody UpdateProductPriceDTO productPriceDTO) {
        try {
            core.updateProduct(product_id, productPriceDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
