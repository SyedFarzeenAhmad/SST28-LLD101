import java.util.*;

public class CafeteriaSystem {

    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceRepository store;
    private final PricingService pricingService;
    private final TaxPolicy taxPolicy;
    private final DiscountPolicy discountPolicy;
    private final InvoiceFormatter formatter;

    private int invoiceSeq = 1000;

    public CafeteriaSystem() {
        this.store = new FileStore();
        this.pricingService = new PricingService();
        this.taxPolicy = new DefaultTaxPolicy();
        this.discountPolicy = new DefaultDiscountPolicy();
        this.formatter = new InvoiceFormatter();
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(String customerType, List<OrderLine> lines) {

        String invId = "INV-" + (++invoiceSeq);

        InvoiceTotals totals = pricingService.calculate(
                lines, menu, taxPolicy, discountPolicy, customerType
        );

        String printable = formatter.format(invId, lines, menu, totals);

        System.out.print(printable);

        store.save(invId, printable);

        System.out.println("Saved invoice: " + invId +
                " (lines=" + store.countLines(invId) + ")");
    }
}