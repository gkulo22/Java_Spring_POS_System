package ge.POS.design_pattern.core.state;

import ge.POS.design_pattern.core.models.ProductInReceipt;
import ge.POS.design_pattern.core.models.Receipt;

public abstract class ReceiptState {
    public abstract Receipt addProduct(ProductInReceipt product);
    public abstract Receipt changeStatus(Receipt receipt);
}
