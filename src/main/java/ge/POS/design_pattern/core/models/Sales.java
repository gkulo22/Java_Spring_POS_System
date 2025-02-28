package ge.POS.design_pattern.core.models;

import lombok.Data;

import java.util.List;

@Data
public class Sales {
    private int n_receipts = 0;
    private double revenue = 0.0;

    public Sales count_revenue(List<Receipt> receipts) {
        for (Receipt receipt : receipts) {
            if (!receipt.getStatus()) {
                n_receipts += 1;
                revenue += receipt.getTotal();
            }
        }

        Sales sales = new Sales();
        sales.setRevenue(revenue);
        sales.setN_receipts(n_receipts);
        return sales;
    }
}
