package uz.alano.warehouse.product;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Food.class, name = "food"),
        @JsonSubTypes.Type(value = Appliance.class, name = "appliance"),
        @JsonSubTypes.Type(value = Clothes.class, name = "clothes")
})
public abstract class Product {
    private long ean;
    private double price;
    private String name;

    public long getEan(){
        return this.ean;
    }

    public void setEan(long ean) {
        if (ean <= 0){
            throw new IllegalArgumentException();
        }

        this.ean = ean;
    }

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double price){
        if (price < 0){
            throw new IllegalArgumentException();
        }

        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException();
        }

        this.name = name;
    }

    Product(){}
    Product(long ean, double price, String name){
        this.ean = ean;
        this.price = price;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("\n-----------------\n"
                + "   EAN: %d   Name: %s   Price: %f",
                this.ean,
                this.name,
                this.price);
    }
}
