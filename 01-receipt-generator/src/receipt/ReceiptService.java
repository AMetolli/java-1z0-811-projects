package receipt;

import java.util.ArrayList;
import java.util.List;

public class ReceiptService {
    private List<ReceiptItem> items = new ArrayList<>();

    
    public void addItem(Product product, int quantity) {
        items.add(new ReceiptItem(product, quantity));
    }

    
    
    public void printReceipt() {
        System.out.println("\n========== DEIN KASSENBON ==========");
        double total = 0;
        double tax7Total = 0;
        double tax19Total = 0;

        for (ReceiptItem item : items) {
            double lineTotal = item.getTotalPrice();
            total += lineTotal;

            
            double rate = item.getProduct().getTaxRate();
            double netPrice = lineTotal / (1.0 + rate);
            double taxAmount = lineTotal - netPrice;

            if (rate == 0.07) {
                tax7Total += taxAmount;
            } else if (rate == 0.19) {
                tax19Total += taxAmount;
            }

            System.out.printf("%dx %-10s -> %.2f EUR\n",
                    item.getQuantity(),
                    item.getProduct().getName(),
                    lineTotal);
        }

        System.out.println("------------------------------------");
        System.out.printf("Gesamtsumme:        %.2f EUR\n", total);
        System.out.printf("  davon 7%% MwSt:    %.2f EUR\n", tax7Total);
        System.out.printf("  davon 19%% MwSt:   %.2f EUR\n", tax19Total);
        System.out.println("====================================\n");
    }
}