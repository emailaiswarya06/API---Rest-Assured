import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class GetStationDataOn15thApril {

    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://api.bart.gov/api").
                build();
    }

    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createResponseSpecification() {

        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @Test
    public void testGetStationDataOn15thAprilAndValidateForStatusCode(){
        given().
            spec(requestSpec).
        when().
            get("route.aspx?cmd=routes&key=MW9S-E7SL-26DU-VV8V&date=04/15/2021&json=y").
        then().
            spec(responseSpec);
    }

    @Test
    public void testGetStationDataOn15thAprilAndValidateForContentType(){
        given().
             spec(requestSpec).
        when().
             get("route.aspx?cmd=routes&key=MW9S-E7SL-26DU-VV8V&date=04/15/2021&json=y").
        then().
             assertThat().
                spec(responseSpec);
    }

    @Test
    public void testGetStationDataOn15thAprilAndLogRequestAndResponseDetails(){
        given().
             spec(requestSpec).
             log().all().
        when().
             get("route.aspx?cmd=routes&key=MW9S-E7SL-26DU-VV8V&date=04/15/2021&json=y").
        then().
             spec(responseSpec).and().
             log().body();
    }

    @Test
    public void testGetStationDataOn15thAprilAndValidateResponseBody(){
        given().
              spec(requestSpec).
        when().
              get("route.aspx?cmd=routes&key=MW9S-E7SL-26DU-VV8V&date=04/15/2021&json=y").
        then().
              spec(responseSpec).and().
              assertThat().
                body("root.routes.route[0].name", equalTo("Oakland Airport to Coliseum")).
                body("root.routes.route.routeID", hasItem("ROUTE 20")).
                body("root.routes.route.routeID", not(hasItem("ROUTE 0"))).
                body("root.routes.route.number", hasSize(12));
    }

    @Test
    public void testGetStationDataOn15thAprilAndPrintAndValidateName(){
        String name =
        given().
              spec(requestSpec).
        when().
              get("route.aspx?cmd=routes&key=MW9S-E7SL-26DU-VV8V&date=04/15/2021&json=y").
        then().
              extract().
                path("root.routes.route[0].name");

        System.out.println("Name of route is : " + name);
        Assert.assertEquals("Oakland Airport to Coliseum", name);

    }

}
