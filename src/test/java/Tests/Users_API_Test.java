package Tests;

import Base.BaseTest;
import Utility.Property;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Users_API_Test extends BaseTest {
    Property config = new Property();
    public String bearerToken;
    JsonPath jsonConfig;
// Login User
    @Test
    public void createPost_Login() throws IOException {
        // Read JSON file as String
        String content = new String(Files.readAllBytes(Paths.get(config.property.getProperty("LoginUser_Json"))));

        // Convert string to JSONObject
        JSONObject requestBody = new JSONObject(content);

        // Print JSON object
        System.out.println(requestBody.toString(4));
        Response response =  given()
                .spec(requests())
                .basePath(config.property.getProperty("basePath_Users"))
                .body(requestBody.toString())
                .when()
                .post(config.property.getProperty("Login"));
        //Validate Response
        response.then()
                .assertThat().statusCode(200)
                .log().all();
        System.out.println(response.getStatusCode()+":"+response.getStatusLine());

        // Extract request body values
//       String value= response.path("token").toString();
//        System.out.println(value);
        jsonConfig = response.jsonPath();
        bearerToken = jsonConfig.getString("token");
    }

    @Test
    public void createPost_AddUser(){

    }


    @Test
    public void createGetUser(){

    }


}
