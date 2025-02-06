package Base;

import Utility.Property;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class BaseTest {
  Property config= new Property();
    RequestSpecification requestSpec ;
    ResponseSpecification responseSpec;

    public RequestSpecification requests(){
      requestSpec= new RequestSpecBuilder()
              .setBaseUri(config.property.getProperty("baseURL"))
              .setContentType(ContentType.JSON)
              .setAccept(ContentType.JSON)
              .log(LogDetail.ALL)
              .build();
      return requestSpec;
    }

    ResponseSpecification responses(){
      responseSpec = new ResponseSpecBuilder()
              .expectContentType(config.property.getProperty("contentType"))
              .log(LogDetail.ALL)
              .build();
        return responseSpec;
    }

  /* public String getBearerToken() throws IOException {
      // Read JSON file as String
      String content = new String(Files.readAllBytes(Paths.get(config.property.getProperty("LoginUser_Json"))));
      // Convert string to JSONObject
      JSONObject requestBody = new JSONObject(content);
      Response response = given()
              .spec(requests())
              .basePath(config.property.getProperty("basePath_Users"))
              .body(requestBody.toString())
              .when()
              .post(config.property.getProperty("Login"));
      JsonPath jsonConfig = response.jsonPath();
      bearerToken = jsonConfig.getString("token");
      return bearerToken;
    }*/
}
