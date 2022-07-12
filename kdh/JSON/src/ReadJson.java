import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadJson {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        Path path = Paths.get("./sample.json");
        String wholeData = new String(Files.readAllBytes(path));

        Gson gson = new Gson();
        JsonObject jsonObj = gson.fromJson(wholeData, JsonObject.class);

        for (String key : jsonObj.keySet())
        {
            System.out.print("Key : "+key+" / Value Type : ");

            JsonElement je = jsonObj.get(key);

            if (je.isJsonPrimitive()) {
                if (je.getAsJsonPrimitive().isString()) {
                    System.out.println("String");
                }
                else if (je.getAsJsonPrimitive().isNumber())
                {
                    System.out.println("Number");
                }
                else if (je.getAsJsonPrimitive().isBoolean()) {
                    System.out.println("Boolean");
                }
                else if (je.getAsJsonPrimitive().isJsonNull()) {
                    System.out.println("null");
                }
            }
            else if (je.isJsonArray()) {
                System.out.println("Array");
            }
            else if (je.isJsonObject()) {
                System.out.println("Object");
            }
            else if (je.isJsonNull()) {
                System.out.println("null");
            }

        }

        //System.out.println(str);
    }
}
