import java.util.*;

public class HostelFeeCalculator {

    private final FakeBookingRepo repo;
    private final RoomPricingFactory roomFactory;
    private final AddOnPricingFactory addOnFactory;

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this.repo = repo;
        this.roomFactory = new RoomPricingFactory();
        this.addOnFactory = new AddOnPricingFactory();
    }

    public void process(BookingRequest req) {

        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {

        Money total = roomFactory.get(req.roomType).monthly();

        for (AddOn a : req.addOns) {
            AddOnPricing pricing = addOnFactory.get(a);
            if (pricing != null) {
                total = total.plus(pricing.monthly());
            }
        }

        return total;
    }
}