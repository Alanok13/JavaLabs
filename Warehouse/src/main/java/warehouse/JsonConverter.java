package warehouse;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

class JsonConverter {
    public static void toJSON(WareHouse wareHouse, String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(path), wareHouse);
    }

    public static WareHouse toJavaObject(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(path), WareHouse.class);
    }

}