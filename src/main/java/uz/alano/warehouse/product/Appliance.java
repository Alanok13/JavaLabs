package uz.alano.warehouse.product;

import com.fasterxml.jackson.annotation.JsonTypeName;

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

    public Appliance(){}
    public Appliance(long ean, double price, String name, int inputPower) {
        super(ean, price, name);
        this.inputPower = inputPower;
    }

    @Override
    public String toString() {
        String info = String.format("\n   Input power: %d", this.inputPower);

        return super.toString() + info;
    }
}
