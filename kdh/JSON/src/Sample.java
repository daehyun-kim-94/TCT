import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Sample {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("key", "value");
        System.out.println(jsonObj.toString());
    }
}