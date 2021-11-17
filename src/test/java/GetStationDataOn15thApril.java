import io.restassured.http.ContentType;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class GetStationDataOn15thApril {

    @Test
    public void testGetStationDataOn15thAprilAndValidateForStatusCode(){
        given().
        when().
            get("https://api.bart.gov/api/route.aspx?cmd=routes&key=MW9S-E7SL-26DU-VV8V&date=04/15/2021&json=y").
        then().
            assertThat().
                statusCode(200);
    }

    @Test
    public void testGetStationDataOn15thAprilAndValidateForContentType(){
        given().
        when().
             get("https://api.bart.gov/api/route.aspx?cmd=routes&key=MW9S-E7SL-26DU-VV8V&date=04/15/2021&json=y").
        then().
             assertThat().
//                contentType("application/json");
                  contentType(ContentType.JSON);
    }

    @Test
    public void testGetStationDataOn15thAprilAndLogRequestAndResponseDetails(){
        given().
             log().all().
        when().
             get("https://api.bart.gov/api/route.aspx?cmd=routes&key=MW9S-E7SL-26DU-VV8V&date=04/15/2021&json=y").
        then().
             log().body();
    }

    @Test
    public void testGetStationDataOn15thAprilAndValidateResponseBody(){
        given().
        when().
              get("https://api.bart.gov/api/route.aspx?cmd=routes&key=MW9S-E7SL-26DU-VV8V&date=04/15/2021&json=y").
        then().
              assertThat().
                body("root.routes.route[0].name", equalTo("Oakland Airport to Coliseum")).
                body("root.routes.route.routeID", hasItem("ROUTE 20")).
                body("root.routes.route.routeID", not(hasItem("ROUTE 0"))).
                body("root.routes.route.number", hasSize(12));
    }
}
