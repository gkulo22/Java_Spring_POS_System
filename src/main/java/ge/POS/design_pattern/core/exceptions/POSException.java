package ge.POS.design_pattern.core.exceptions;

import lombok.Data;

@Data
public class POSException extends Exception {
    private Error error;
}
