package ge.POS.design_pattern.core.exceptions;

public class UnitNotFoundException extends POSException {
    public UnitNotFoundException(String id) {
        super.setError(new Error("unit with id: " + id + " does not exist."));
    }
}
