package Serialization;

import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreateUser {
    @Test
    public void createUser(){

//        String requestBody = "{\n" +
//                "    \"name\": \"morpheus\",\n" +
//                "    \"job\": \"leader\"\n" +
//                "}";
        UserCreateRequestModel userRequestBody = new UserCreateRequestModel();
        userRequestBody.setName("morpheus");
        userRequestBody.setJob("leader");
        UserCreateResponseModel userResponseBody =
                given().
                contentType(ContentType.JSON).body(userRequestBody).
                when().
                post("https://reqres.in/api/users").as(UserCreateResponseModel.class);
//                then().
//                assertThat().statusCode(201);

        System.out.println(userResponseBody);

        Assert.assertEquals("morpheus",userResponseBody.getName());
    }
}
