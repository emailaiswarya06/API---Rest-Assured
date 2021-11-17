import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetStationDataOn15thApril {

    @Test
    public void testGetStationDataOn15thAprilAndValidateRouteName(){
        Response response = given().
        when().
            get("https://api.bart.gov/api/route.aspx?cmd=routes&key=MW9S-E7SL-26DU-VV8V&date=04/15/2021&json=y").
        then().
            assertThat().
                statusCode(200).
                body("root.routes.route[0].name", equalTo("Oakland Airport to Coliseum")).
                and().extract().response();

        System.out.println("Routes information for 15-04-2021 : "+ response.asString());

    }
}
