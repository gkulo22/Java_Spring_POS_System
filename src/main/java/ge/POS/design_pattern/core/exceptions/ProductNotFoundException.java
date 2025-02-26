package ge.POS.design_pattern.core.exceptions;

public class ProductNotFoundException extends POSException {
    public ProductNotFoundException(String id) {
        super.setError(new Error("product with id: " + id + " does not exist."));
    }
}
