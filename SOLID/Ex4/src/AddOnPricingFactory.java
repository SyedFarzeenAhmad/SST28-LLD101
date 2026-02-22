import java.util.*;

public class AddOnPricingFactory {

    private final Map<AddOn, AddOnPricing> registry = new HashMap<>();

    public AddOnPricingFactory() {
        registry.put(AddOn.MESS, new MessAddOn());
        registry.put(AddOn.LAUNDRY, new LaundryAddOn());
        registry.put(AddOn.GYM, new GymAddOn());
    }

    public AddOnPricing get(AddOn addOn) {
        return registry.get(addOn);
    }
}