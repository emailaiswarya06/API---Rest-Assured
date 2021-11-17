import dataentities.PageDetails;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetListOfUsers {


    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api").
                build();
    }

    @Test
    public void testGetListOfUsersAndPrintResponseBody() {

        int pageNo=2;
        Response response =
                given().
                     queryParam("page", pageNo).
                     spec(requestSpec).
                when().
                     get("users").
                then().extract().response();
        System.out.println(response.asString());
    }


    @Test
    public void testGetListOfUsersAndValidateFirstName() {

        int pageNo=2;

        PageDetails pageDetails =

                given().
                     queryParam("page", pageNo).
                     spec(requestSpec).
                when().
                     get("users").
                     as(PageDetails.class);

        Assert.assertEquals(
                "Lindsay",
                pageDetails.getData().get(1).getFirstName()
        );

    }
}
