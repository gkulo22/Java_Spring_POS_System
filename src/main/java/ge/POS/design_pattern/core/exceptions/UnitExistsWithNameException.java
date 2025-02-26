package ge.POS.design_pattern.core.exceptions;

import lombok.Data;

@Data
public class UnitExistsWithNameException extends POSException{
    public UnitExistsWithNameException(String name){
        super.setError(new Error("unit with name: " + name + " already exists"));
    }
}
