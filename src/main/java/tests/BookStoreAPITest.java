package tests;

import static data.Credentials.PASSWORD;
import static data.Credentials.USERNAME;
import static data.Credentials.USER_ID;
import static data.URLS.BOOKS_API;
import static data.URLS.GENERATE_TOKEN_API;
import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookStoreAPITest {
    //TODO: Normally it is required to create POJO for body part but it's time consuming
    @Test()
    public void apiTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        //Get token
        Response tokenResponse = given()
                .contentType(ContentType.JSON)
                .when()
                .body("{\n"
                        + "  \"userName\": \"" + USERNAME + "\",\n"
                        + "  \"password\": \"" + PASSWORD + "\"\n"
                        + "}")
                .post(GENERATE_TOKEN_API)
                .then()
                .extract().response();

        Assert.assertEquals(200, tokenResponse.statusCode());
        JsonNode actualObj1 = mapper.readTree(tokenResponse.body().asString());
        String token = actualObj1.get("token").asText();

        //remove all books from collection
        Response removeAllBooksResponse = given().headers(
                        "Authorization",
                        "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .delete(BOOKS_API + "?UserId=" + USER_ID)
                .then()
                .extract().response();

        Assert.assertEquals(204, removeAllBooksResponse.statusCode());

        //get list of all books
        Response listOfBooksResponse = given()
                .contentType(ContentType.JSON)
                .when()
                .get(BOOKS_API)
                .then()
                .extract().response();
        Assert.assertEquals(200, listOfBooksResponse.statusCode());

        //take isbn of the first book
        JsonNode actualObj = mapper.readTree(listOfBooksResponse.body().asString());
        JsonNode entries = actualObj.get("books");
        String isbn = entries.get(0).get("isbn").asText();

        //add book to collection
        Response response2 = given().headers(
                        "Authorization",
                        "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .body("{\n"
                        + "  \"userId\": \"" + USER_ID + "\", \n"
                        + "  \"collectionOfIsbns\": [\n"
                        + "    {\n"
                        + "      \"isbn\": \"" + isbn + "\"\n"
                        + "    }\n"
                        + "  ]\n"
                        + "}")
                .post(BOOKS_API)
                .then()
                .extract().response();

        Assert.assertEquals(201, response2.statusCode());
    }
}
