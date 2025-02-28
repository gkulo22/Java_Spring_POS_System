package ge.POS.design_pattern.core.exceptions;

public class ReceiptNotFoundException extends POSException {
    public ReceiptNotFoundException(String id) {
      super.setError(new Error("receipt with id: " + id + " does not exist."));
    }
}
