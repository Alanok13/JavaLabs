package uz.alano.warehouse.comparators;

import uz.alano.warehouse.product.Clothes;

import java.util.Comparator;

public class ClothesComparatorBySize implements Comparator<Clothes> {
    @Override
    public int compare(Clothes o1, Clothes o2) {
        return Integer.compare(o1.getSize(), o2.getSize());
    }
}
