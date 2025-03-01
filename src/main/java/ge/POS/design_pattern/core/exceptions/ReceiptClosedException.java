package ge.POS.design_pattern.core.exceptions;

public class ReceiptClosedException extends POSException {
    public ReceiptClosedException(String id) {
      super.setError(new Error("receipt with id: " + id + " is closed."));
    }
}
