import dataentities.PageDetails;
import dataentities.UserInfo;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.hasToString;

public class GetSingleUser {

    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api").
                build();
    }

    @Test
    public void testGetSingleUserAndPrintResponseBody() {
        int id = 2;
        String name =
                given().
                     contentType(ContentType.JSON).
                     pathParam("id", id).
                     spec(requestSpec).
                when().
                     get("https://reqres.in/api/users/{id}").
                then().
                     assertThat().statusCode(200).
                     body("data.id", comparesEqualTo(id)).
                     body("data.email", hasToString("janet.weaver@reqres.in")).
                     and().extract().path("data.first_name");

        System.out.println("Name of the user is : " + name);
    }

    @Test
    public void testGetSingleUserAndValidateFirstName() {

        int id=2;

        UserInfo pageDetails =

                given().
                     contentType(ContentType.JSON).
                     pathParam("id", id).
                     spec(requestSpec).
                when().
                     get("https://reqres.in/api/users/{id}").
                        as(UserInfo.class);

        Assert.assertEquals(
                "Janet",
                pageDetails.getData().getFirstName()
        );
    }
}
