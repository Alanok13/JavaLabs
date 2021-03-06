package uz.alano.warehouse.comparators;

import uz.alano.warehouse.product.Appliance;

import java.util.Comparator;

public class ApplianceComparatorByInputPower implements Comparator<Appliance> {
    @Override
    public int compare(Appliance o1, Appliance o2) {
        return Integer.compare(o1.getInputPower(), o2.getInputPower());
    }
}
