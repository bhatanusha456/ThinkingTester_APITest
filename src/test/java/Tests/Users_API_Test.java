package Tests;

import Base.BaseTest;
import Utility.JsonReader;
import Utility.Property;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Users_API_Test extends BaseTest {
    Property config = new Property();
    public String path;
    JsonPath jsonConfig;
    JsonReader jsonfile = new JsonReader();
    String file = "src/main/java/JsonResponse/GetUserProfile.json";
    String file1= "src/main/java/JsonResponse/LoginResponse.json";

// Login User
    @Test
    public void createPost_Login() throws IOException {
        String file = "src/main/java/JsonResponse/LoginResponse.json";
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
        // Write the response in a json file
        jsonfile.writeJsonFile(response,file);
    }

    @Test
    public void createPost_AddUser() throws IOException {
        path="token";
        String jsonRead = new String(Files.readAllBytes(Paths.get(config.property.getProperty("AddUser_Json"))));
        JSONObject requestBody = new JSONObject(jsonRead);

        Response response = given()
                .spec(requests())
                .basePath(config.property.getProperty("basePath_Users"))
                .header("Authorization","Bearer "+jsonfile.readJsonFile("src/main/java/JsonResponse/LoginResponse.json",path))
                .body(requestBody.toString())
                .when()
                .post();
        response.then()
                .statusCode(400)
                .log().all();
    }


    @Test
    public void createGetUser() throws IOException {
        path="token";
        Response response = given()
                .spec(requests())
                .basePath(config.property.getProperty("basePath_Users"))
                .header("Authorization","Bearer "+jsonfile.readJsonFile("src/main/java/JsonResponse/LoginResponse.json",path))
                .when()
                .get(config.property.getProperty("UserProfile"));

        response.then()
                .statusCode(200)
                .log().all();
        jsonfile.writeJsonFile(response,file);
       // System.out.println(jsonfile.readJsonFile(file1,"user._id"));
        assertThat(jsonfile.readJsonFile(file1,"user._id"),equalTo(jsonfile.readJsonFile(file,"_id")));
    }

    @Test
    void create_Update_User_request() throws IOException {
        String filepath = "src/test/java/Specs/UpdateUser.json";
         path="token";
        Response response = given()
                .spec(requests())
                .basePath(config.property.getProperty("basePath_Users"))
                .header("Authorization","Bearer "+jsonfile.readJsonFile(file1,path))
                .body(jsonfile.readJsonFile(filepath))
                .when()
                .patch(config.property.getProperty("UserProfile"));
        response.then()
                .log().all()
                .statusCode(400);
    }
}
