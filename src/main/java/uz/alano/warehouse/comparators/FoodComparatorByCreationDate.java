package uz.alano.warehouse.comparators;

import uz.alano.warehouse.product.Food;

import java.util.Comparator;

class FoodComparatorByCreationDate implements Comparator<Food> {
    @Override
    public int compare(Food o1, Food o2) {
        return o1.getCreationDate().compareTo(o2.getCreationDate());
    }
}
