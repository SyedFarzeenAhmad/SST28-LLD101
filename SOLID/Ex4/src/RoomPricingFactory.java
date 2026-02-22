import java.util.*;

public class RoomPricingFactory {

    private final Map<Integer, RoomPricing> registry = new HashMap<>();

    public RoomPricingFactory() {
        registry.put(LegacyRoomTypes.SINGLE, new SingleRoomPricing());
        registry.put(LegacyRoomTypes.DOUBLE, new DoubleRoomPricing());
        registry.put(LegacyRoomTypes.TRIPLE, new TripleRoomPricing());
        registry.put(LegacyRoomTypes.DELUXE, new DeluxeRoomPricing());
    }

    public RoomPricing get(int roomType) {
        return registry.getOrDefault(roomType, new DeluxeRoomPricing());
    }
}