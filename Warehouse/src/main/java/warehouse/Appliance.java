package warehouse;

import org.codehaus.jackson.annotate.*;

@JsonTypeName("appliance")
public class Appliance extends Product {
    private int inputPower;

    public int getInputPower(){
        return this.inputPower;
    }

    public void setInputPower(int inputPower){
        if (inputPower < 0){
            throw new IllegalArgumentException();
        }

        this.inputPower = inputPower;
    }

    @JsonCreator
    Appliance(@JsonProperty("ean") long ean,
              @JsonProperty("price") double price,
              @JsonProperty("name") String name,
              @JsonProperty("inputPower") int inputPower) {
        super(ean, price, name);
        this.inputPower = inputPower;
    }

    @Override
    public String toString() {
        String info = String.format("\n   Input power: %d", this.inputPower);

        return super.toString() + info;
    }
}
