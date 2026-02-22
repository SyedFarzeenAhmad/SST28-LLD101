public class InvoiceTotals {
    public final double subtotal;
    public final double taxPercent;
    public final double tax;
    public final double discount;
    public final double total;

    public InvoiceTotals(double subtotal, double taxPercent,
                         double tax, double discount, double total) {
        this.subtotal = subtotal;
        this.taxPercent = taxPercent;
        this.tax = tax;
        this.discount = discount;
        this.total = total;
    }
}