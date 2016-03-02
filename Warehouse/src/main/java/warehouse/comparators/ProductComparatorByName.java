package warehouse.comparators;

import java.util.Comparator;

public class ProductComparatorByName implements Comparator<warehouse.Product> {
    @Override
    public int compare(warehouse.Product o1, warehouse.Product o2) {
        return o1.getName().compareTo(o2.getName());
    }
}


