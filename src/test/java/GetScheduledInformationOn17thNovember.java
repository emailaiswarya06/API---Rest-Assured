import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class GetScheduledInformationOn17thNovember {

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
                expectContentType(ContentType.XML).
                build();
    }

    @Test
    public void testGetDepartureInformationAndValidateResponseBodyForXMLResponseOn17thNovember(){
        given().
             spec(requestSpec).
        when().
             get("sched.aspx?cmd=depart&orig=ASHB&dest=CIVC&date=11/17/2021&key=MW9S-E7SL-26DU-VV8V&b=2&a=2&l=1").
        then().
             spec(responseSpec).and().
             assertThat().
                body("root.schedule.request.trip.fares.fare[1].@name", equalTo("Clipper START")).
                body("root.schedule.request.trip.fares.findAll{it.@level!='normal'}", empty());
    }
}



