package Tests;

import Base.BaseTest;
import Utility.JsonReader;
import Utility.Property;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Contacts_API extends BaseTest {
Property config = new Property();
JsonReader jsonfile = new JsonReader();
String path;
String file = "src/main/java/JsonResponse/LoginResponse.json";
String addcontactFile ="src/test/java/Specs/Add_Contact.json";
String ContactList = "src/main/java/JsonResponse/ContactLists.json";
    @Test
    void Add_Contact() throws IOException {
        path = "token";
        Response response = given()
                .spec(requests())
                .basePath(config.property.getProperty("basePath_Contacts"))
                .header("Authorization","Bearer "+jsonfile.readJsonFile(file,path))
                .body(jsonfile.readJsonFile(addcontactFile))
                .when()
                .post();

        response.then()
                .statusCode(200)
                .log().all();
    }

    @Test
    void  get_ContactList() throws IOException {
        path="token";

        Response response = given()
                .spec(requests())
                .basePath(config.property.getProperty("basePath_Contacts"))
                .header("Authorization","Bearer "+jsonfile.readJsonFile(file,path))
                .when()
                .get();
        response.then()
                .statusCode(200)
                .log().all();
        jsonfile.writeJsonFile(response,ContactList);
    }

    @Test
    void getContactList() throws IOException {
        path="token";

        Response response = given()
                .spec(requests())
                .basePath(config.property.getProperty("basePath_Contacts"))
                .header("Authorization","Bearer "+jsonfile.readJsonFile(file,path))
                .when()
                .get(jsonfile.readJsonFile(ContactList,"user._id[4]"));
        response.then()
                .statusCode(200)
                .log().all();
        jsonfile.writeJsonFile(response,ContactList);
    }

    @Test
    void createUpdateContact() throws IOException {
        path="token";

        Response response = given()
                .spec(requests())
                .basePath(config.property.getProperty("basePath_Contacts"))
                .header("Authorization","Bearer "+jsonfile.readJsonFile(file,path))
                .body(jsonfile.readJsonFile(config.property.getProperty("Update_Contact_Document")))
                .when()
                .put(jsonfile.readJsonFile(ContactList,"user._id[3]"));
        response.then()
                .statusCode(200)
                .log().all();
    }

    @Test
    void createUpdate_A_Contact() throws IOException {
        path="token";

        Response response = given()
                .spec(requests())
                .basePath(config.property.getProperty("basePath_Contacts"))
                .header("Authorization","Bearer "+jsonfile.readJsonFile(file,path))
                .body(jsonfile.readJsonFile(config.property.getProperty("Update_Contact")))
                .when()
                .patch(jsonfile.readJsonFile(ContactList,"user._id[3]"));
        response.then()
                .statusCode(200)
                .log().all();
    }

    @Test
    void create_delete_Contact(){
        path="token";

        Response response = given()
                .spec(requests())
                .basePath(config.property.getProperty("basePath_Contacts"))
                .
    }
}
