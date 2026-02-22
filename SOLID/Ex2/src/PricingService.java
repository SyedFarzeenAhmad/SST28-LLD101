import java.util.*;

public class PricingService {

    public InvoiceTotals calculate(
            List<OrderLine> lines,
            Map<String, MenuItem> menu,
            TaxPolicy taxPolicy,
            DiscountPolicy discountPolicy,
            String customerType
    ) {
        double subtotal = 0.0;

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            subtotal += item.price * l.qty;
        }

        double taxPct = taxPolicy.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);

        double discount = discountPolicy.discountAmount(
                customerType, subtotal, lines.size()
        );

        double total = subtotal + tax - discount;

        return new InvoiceTotals(subtotal, taxPct, tax, discount, total);
    }
}