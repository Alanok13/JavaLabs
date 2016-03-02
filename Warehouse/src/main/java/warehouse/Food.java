package warehouse;

import org.codehaus.jackson.annotate.*;
import java.util.Date;

@JsonTypeName("food")
public class Food extends Product {
    private int calorie;
    private Date creationDate;
    private int expirationTime;

    public int getCalorie() {
        return this.calorie;
    }

    public void setCalorie(int calorie) {
        if (calorie < 0) {
            throw new IllegalArgumentException();
        }

        this.calorie = calorie;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        if (date == null || date.after(new Date())) {
            throw new IllegalArgumentException();
        }
    }

    public int getExpirationTime() {
        return this.expirationTime;
    }

    public void setExpirationTime(int expirationTime) {
        if (expirationTime < 0) {
            throw new IllegalArgumentException();
        }

        this.expirationTime = expirationTime;
    }

    @JsonCreator
    Food(@JsonProperty("ean") long ean,
         @JsonProperty("price") double price,
         @JsonProperty("name") String name,
         @JsonProperty("calorie") int calorie,
         @JsonProperty("creationDate") Date creationDate,
         @JsonProperty("expirationTime") int expirationTime) {
        super(ean, price, name);

        this.calorie = calorie;
        this.creationDate = creationDate;
        this.expirationTime = expirationTime;
    }

    @Override
    public String toString() {
        String info = String.format("\n   Calories: %d   Creation: %s   Expiration: %d",
                this.calorie,
                this.creationDate,
                this.expirationTime);

        return super.toString() + info;
    }
}
