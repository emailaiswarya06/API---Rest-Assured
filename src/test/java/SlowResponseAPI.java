import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

import org.junit.Test;

import static io.restassured.RestAssured.given;


@Epic("Login Tests Epic")
@Feature("Login Tests Epic")
public class SlowResponseAPI {

    @Test
    @Story("Login Tests Epic")
    @Description("Login Tests Epic")
    public void testGetListOfUsersAndPrintResponseBody() {

        RestAssured.config= RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.socket.timeout",10)
                        .setParam("http.connection.timeout", 10));

        Response response =
                given().
                when().
                     get("http://www.fakeresponse.com/").
                     then().extract().response();
        System.out.println(response.asString());
    }

    @Test
    @Story("Login Tests Epic")
    @Description("Login Tests Epic")
    public void testGetListsersAndPrintResponseBody() {

        try{
                given().
                when().
                     get("http://www.fakeresponse.com/");
        } catch (Exception e){
            System.out.println("the test failed");
        }

    }

}
