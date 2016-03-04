package uz.alano.warehouse.comparators;

import uz.alano.warehouse.product.Food;

import java.util.Comparator;

public class FoodComparatorByCalorie implements Comparator<Food> {
    @Override
    public int compare(Food o1, Food o2) {
        return Integer.compare(o1.getCalorie(), o2.getCalorie());
    }
}
