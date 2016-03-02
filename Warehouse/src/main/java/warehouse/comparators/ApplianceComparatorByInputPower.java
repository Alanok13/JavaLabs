package warehouse.comparators;

import warehouse.Appliance;
import java.util.Comparator;

public class ApplianceComparatorByInputPower implements Comparator<Appliance> {
    @Override
    public int compare(Appliance o1, Appliance o2) {
        return Integer.compare(o1.getInputPower(), o2.getInputPower());
    }
}
