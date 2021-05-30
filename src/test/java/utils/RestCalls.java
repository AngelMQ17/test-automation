package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class RestCalls {
    private static String BASE_URI = "http://localhost:3333/api";

    public String createNewUser(Data data) {
        String randomEmail = RandomStringUtils.random(10, true, true) + data.getUser().getEmail();
        String randomName = data.getUser().getUsername() + RandomStringUtils.random(5, true, true);
        data.getUser().setEmail(randomEmail);
        data.getUser().setUsername(randomName);

        Response response =
        given()
                .baseUri(BASE_URI + "/users")
                .accept("application/json")
                .contentType(ContentType.JSON)
                .body(data.getUser())
                .post();

        Assert.assertTrue(response.getStatusCode() == 201, "The user is not created correctly");

        return randomEmail;
    }
}