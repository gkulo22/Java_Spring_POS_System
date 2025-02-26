package ge.POS.design_pattern.core.DTOs;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateProductDTO {
    private String unit_id;
    private String product_name;
    private String product_barcode;
    private double product_price;
}
