import com.google.gson.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class SaveJson {
    public static void main(String[] args) {
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("name", "spiderman");
        jsonObj.addProperty("age", 45);
        jsonObj.addProperty("married", true);

        JsonArray jsonArr = new JsonArray();
        jsonArr.add("martial art");
        jsonArr.add("gun");
        jsonObj.add("specialty", jsonArr);

        JsonObject jsonObj2 = new JsonObject();
        jsonObj2.addProperty("1st", "done");
        jsonObj2.addProperty("2nd", "expected");
        jsonObj2.add("3rd", null);
        jsonObj.add("vaccine", jsonObj2);

        JsonArray jsonArr2 = new JsonArray();
        jsonObj2 = new JsonObject();
        jsonObj2.addProperty("name", "spiderboy");
        jsonObj2.addProperty("age", 10);
        jsonArr2.add(jsonObj2);

        jsonObj2 = new JsonObject();
        jsonObj2.addProperty("name", "spidergirl");
        jsonObj2.addProperty("age", 8);
        jsonArr2.add(jsonObj2);

        jsonObj.add("children", jsonArr2);
        jsonObj.add("address", null);
        Gson gson;
        try (Writer writer = new FileWriter("./sample.json"))
        {
            gson = new GsonBuilder().serializeNulls().create();
            gson.toJson(jsonObj, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());

        } catch (JsonIOException e) {
            System.out.println(e.getMessage());
        }finally {
        }

    }
}
