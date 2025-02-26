package ge.POS.design_pattern.core.exceptions;

public class ProductExistsWithBarcodeException extends POSException {
    public ProductExistsWithBarcodeException(String barcode) {
        super.setError(new Error("product with barcode: " + barcode + " already exists."));
    }
}
