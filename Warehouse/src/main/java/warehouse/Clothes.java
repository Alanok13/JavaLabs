package warehouse;

import org.codehaus.jackson.annotate.*;

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

    @JsonCreator
    Clothes(@JsonProperty("ean") long ean,
            @JsonProperty("price") double price,
            @JsonProperty("name") String name,
            @JsonProperty("size") byte size,
            @JsonProperty("material") String material) {
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
