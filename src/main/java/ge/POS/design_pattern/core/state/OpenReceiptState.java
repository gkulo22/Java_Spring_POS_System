package ge.POS.design_pattern.core.state;

import ge.POS.design_pattern.core.models.ProductInReceipt;
import ge.POS.design_pattern.core.models.Receipt;

public class OpenReceiptState extends ReceiptState {

    @Override
    public Receipt addProduct(ProductInReceipt product) {
        return null;
    }

    @Override
    public Receipt changeStatus(Receipt receipt) {
        return null;
    }
}
