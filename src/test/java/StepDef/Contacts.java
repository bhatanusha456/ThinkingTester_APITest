package StepDef;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Contacts {
    String baseUrl = "https://thinking-tester-contact-list.herokuapp.com";
    String queryParam = "/contacts";
    String contentType = "application/json";
    String jsonString = "{\n" +
            "    \"firstName\": \"John\",\n" +
            "    \"lastName\": \"Doe\",\n" +
            "    \"birthdate\": \"1970-01-01\",\n" +
            "    \"email\": \"jdoe@fake.com\",\n" +
            "    \"phone\": \"8005555555\",\n" +
            "    \"street1\": \"1 Main St.\",\n" +
            "    \"street2\": \"Apartment A\",\n" +
            "    \"city\": \"Anytown\",\n" +
            "    \"stateProvince\": \"KS\",\n" +
            "    \"postalCode\": \"12345\",\n" +
            "    \"country\": \"USA\"\n" +
            "}";
    Response response;
    @Before
    RequestSpecification reqSpec(){
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBasePath(baseUrl)
                .addQueryParam(queryParam)
                .addHeader("Content-Type",contentType)
                .setBody(jsonString)
                .log(LogDetail.ALL).build();
        return requestSpec;
    }
    @Given("API URL for add contact")
    public void apiURLForAddContact() {
             response=   given()
                     .spec(reqSpec())
                     .body(jsonString)
                     .when()
                     .post();

    }

    @When("Add contact using post request")
    public void addContactUsingPostRequest() {
        System.out.println(response.getStatusCode() + ":"+response.getStatusLine());
    }

    @Then("Verify the response code and response json")
    public void verifyTheResponseCodeAndResponseJson() {
        RestAssured.given().then()
                .response().statusCode(response.getStatusCode())
                .statusLine(response.getStatusLine());
    }
}
