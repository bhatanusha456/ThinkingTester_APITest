package Utility;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader {
    public String readJsonFile(String file) throws IOException {
        String responseBody = new String(Files.readAllBytes(Paths.get(file)));
        JsonPath jsonpath = new JsonPath(responseBody);
        return jsonpath.toString();
    }

    public String readJsonFile(String file,String path) throws IOException {
      String responseBody = new String(Files.readAllBytes(Paths.get(file)));
        JsonPath jsonpath = new JsonPath(responseBody);
       return jsonpath.getString(path);
    }

    public void writeJsonFile(Response response,String filename) throws IOException {
       // String responseBody = response.asString();
        FileWriter file = new FileWriter(filename);
        file.write(response.asPrettyString());
        file.close();
    }
}
