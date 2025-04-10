package api.tests;

import api.models.User;
import api.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserTests {

    private String baseUrl;
    private static String createdUserId;
    private static String createdUserFirstName;
    private static String createdUserLasttName;
    private static String createdUserEmail;
    private static String createdUserJob;

    @BeforeClass
    public void setup() {
        baseUrl = ConfigReader.get("baseUrl");
    }

    @Test
    public void createUserTest() {
        User user = new User("Manal", "Sewaied", "ManalEmail@gmail.com" ,"QA Engineer");

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/api/users")
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post();

        response.then().statusCode(201);

        createdUserId = response.jsonPath().getString("id");
        createdUserFirstName = user.getFirstName();
        createdUserLasttName = user.getLastName();
        createdUserEmail = user.getEmail();
        createdUserJob = user.getJob();

        Assert.assertNotNull(createdUserId, "User ID should not be null");

        System.out.println("User created with ID: " + createdUserId);
    }

    @Test
    public void getUserTest() {
        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/api/users/" + createdUserId)
                .when()
                .get();

        int statusCode = response.getStatusCode();

        if (statusCode == 200) {
            System.out.println("⚠️ Unexpectedly found user with ID " + createdUserId);
        } else {
            System.out.println("Expected 404: Cannot retrieve created user from ReqRes API");
            Assert.assertEquals(statusCode, 404, "Expected 404 since ReqRes doesn't persist created users.");
        }
    }

    @Test
    public void updateUserTest() {
        String updatedFirstName = "Fahad";
        String updatedLastName = "Ahmad";
        String updatedEmail = "FahadA@gmail.com";
        String updatedJob = "system analyst";

        User updatedUser = new User(updatedFirstName, updatedLastName, updatedEmail, updatedJob);

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/api/users/" + createdUserId)
                .contentType(ContentType.JSON)
                .body(updatedUser)
                .when()
                .put();

        response.then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/updateUserSchema.json"));

        String returnedFirstName = response.jsonPath().getString("firstName");
        String returnedLastName = response.jsonPath().getString("lastName");
        String returnedEmail = response.jsonPath().getString("email");
        String returnedJob = response.jsonPath().getString("job");
        String updatedAt = response.jsonPath().getString("updatedAt");

        System.out.println("User updated successfully to: " + " \n " + "First Name: " +returnedFirstName + " \n " +
              "Last Name: " + returnedLastName + " \n " + "Email: " + returnedEmail + " \n " + "Job: " + returnedJob +
                " \n " + "Updated at: " + updatedAt);
    }

    @Test
    public void retrieveStaticUserTest() {
        int userId = 2;

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/api/users/" + userId)
                .when()
                .get();

        response.then().statusCode(200);

        String firstName = response.jsonPath().getString("data.first_name");
        String lastName = response.jsonPath().getString("data.last_name");
        String email = response.jsonPath().getString("data.email");

        Assert.assertEquals(firstName, "Janet");
        Assert.assertEquals(lastName, "Weaver");
        Assert.assertEquals(email, "janet.weaver@reqres.in");

        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Email: " + email);
    }

}


