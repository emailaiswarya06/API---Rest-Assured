package BART;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;



public class GetStationHeaderBody {
    @Test
    public void testGetStationDataOn15thAprilAndValidateForStatusCode(){

        given().

                when().
                get("https://api.bart.gov/api/route.aspx?cmd=routes&key=MW9S-E7SL-26DU-VV8V&date=04/15/2021&json=y").
                then().
                assertThat().statusCode(200);
    }
    @Test
    public void testVerifyRouteName(){

        given().
                when().
                get("https://api.bart.gov/api/route.aspx?cmd=routes&key=MW9S-E7SL-26DU-VV8V&json=y&date=04/15/2021").
                then().
                assertThat().
                body("root.routes.route[0].name",equalTo("Oakland Airport to Coliseum"));
    }

    @Test
    public void testVerifyColor(){

        given().
                when().
                get("https://api.bart.gov/api/route.aspx?cmd=routes&key=MW9S-E7SL-26DU-VV8V&json=y").
                then().
                assertThat().
                body("root.routes.route[1].color",equalTo("BEIGE"));
    }

    @Test
    public void testRouteSize(){

        given().
                when().
                get("https://api.bart.gov/api/route.aspx?cmd=routes&key=MW9S-E7SL-26DU-VV8V&json=y").
                then().
                assertThat().
                body("root.routes.route",hasSize(12)).
                body("root.routes.route.routeID", hasItem("ROUTE 20"));
    }
}