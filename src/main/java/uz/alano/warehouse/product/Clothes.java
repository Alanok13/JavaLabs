package uz.alano.warehouse.product;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("clothes")
public class Clothes extends Product {
    private byte size;
    private String material;

    public byte getSize(){
        return this.size;
    }

    public void setSize(byte size){
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public String getMaterial(){
        return this.material;
    }

    public void setMaterial(String material){
        if (material == null || material.isEmpty()) {
            this.material = material;
        }
    }

    public Clothes(){}
    public Clothes(long ean, double price, String name, byte size, String material) {
        super(ean, price, name);

        this.size = size;
        this.material = material;
    }

    @Override
    public String toString() {
        String info = String.format("\n   Size: %d   Material: %s",
                this.size,
                this.material);

        return super.toString() + info;
    }
}
