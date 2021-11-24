package BART;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class GetDepartureInformation {


        @DataProvider
        public static Object[][] origAndName(){
            return new Object[][]{
                    {"RICH", "Richmond"},
                    {"LAKE", "Lake Merritt"},
                    {"NCON", "North Concord/Martinez"},
                    {"cols", "Coliseum"}

            };
        }

    @Test
    @UseDataProvider("origAndName")
    public void testValidateStationNamesForOrigin(String origin, String originName) {

        String cmd = "etd";
        String key = "MW9S-E7SL-26DU-VV8V";

        given().
                queryParam("orig", origin).queryParam("cmd", cmd).queryParam("key", key).
                when().
                get("https://api.bart.gov/api/etd.aspx").
                then().
                assertThat().
                body("root.station[0].name", equalTo(originName));
    }
}
