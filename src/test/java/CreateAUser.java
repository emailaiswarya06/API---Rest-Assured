import dataentities.PageDetails;
import dataentities.UserDetails;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreateAUser {

    @Test
    public void testCreatingAUserAndPrintingResponse() {
        String requestBody = "{\n" +
                "  \"name\": \"test\",\n" +
                "  \"job\": \"testing\" \n}";

        Response response = given().body(requestBody).
                when().post("https://reqres.in/api/users").
                then().assertThat().statusCode(201).
                and().extract().response();
        System.out.println("Details of user created : "+response.asString());
    }


    @Test
    public void testCreatingAUserAndValidatingStatusCode() {

        UserDetails userDetails = new UserDetails();
        userDetails.setName("Test");
        userDetails.setJob("Testing");

        given().
             contentType(ContentType.JSON).
             body(userDetails).
             log().body().
        when().
             post("https://reqres.in/api/users").
        then().
             assertThat().
               statusCode(201);
    }
}
