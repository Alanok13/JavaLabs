package uz.alano.warehouse.product;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.GregorianCalendar;

@JsonTypeName("food")
public class Food extends Product {
    private int calorie;
    private GregorianCalendar creationDate;
    private int expirationTime;

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        if (calorie < 0) {
            throw new IllegalArgumentException();
        }

        this.calorie = calorie;
    }

    public GregorianCalendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(GregorianCalendar date) {
        if (date == null || date.after(GregorianCalendar.getInstance())) {
            throw new IllegalArgumentException();
        }

        this.creationDate = date;
    }

    public int getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(int expirationTime) {
        if (expirationTime < 0) {
            throw new IllegalArgumentException();
        }

        this.expirationTime = expirationTime;
    }

    public Food(){}
    public Food(long ean, double price, String name, int calorie, GregorianCalendar creationDate, int expirationTime) {
        super(ean, price, name);

        this.calorie = calorie;
        this.creationDate = creationDate;
        this.expirationTime = expirationTime;
    }

    @Override
    public String toString() {
        String info = String.format("\n   Calories: %d   Creation: %s   Expiration: %d",
                calorie,
                creationDate.getTime(),
                expirationTime);

        return super.toString() + info;
    }
}
