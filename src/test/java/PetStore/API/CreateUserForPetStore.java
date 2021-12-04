package PetStore.API;

import PetStore.Model.UserCreateForPetRequestModel;
import PetStore.Model.UserCreateForPetResponseModel;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;
import static io.restassured.RestAssured.given;


@RunWith(DataProviderRunner.class)
public class CreateUserForPetStore {

    static String baseUrl = "https://petstore.swagger.io/v2";
    static String userPath = "/user";
    static String userAllPath = "/user/createWithArray";

     static RequestSpecification requestSpec;


    @DataProvider
    public static Object[] requestUserPayload(){

        UserCreateForPetRequestModel requestUser = new UserCreateForPetRequestModel(101,"jacksonThePetLover","Jackson","Collins",
                "jakson.collins@gmail.com","petLover","9016016011",1);
        return new Object[]{requestUser};
    }


    @DataProvider
    public static Object[] requestUserAllPayload(){
        List<UserCreateForPetRequestModel> requestUserAll = new ArrayList<>();

        UserCreateForPetRequestModel user1 = new UserCreateForPetRequestModel(1110,"PeterForPets","Peter","Parker",
                "peter.parker@gmail.com","PeterForPets","9016527676",1);

        UserCreateForPetRequestModel user2 = new UserCreateForPetRequestModel(2220,"AmyLovesCats","Amy","Williams",
                "amy.williams@gmail.com","AmyLovesCats","9016011122",0);
        requestUserAll.add(user1);
        requestUserAll.add(user2);

        return new Object[]{requestUserAll};


    }

    @BeforeClass
    public static void createRequestSpecification(){
        requestSpec = new RequestSpecBuilder().
                setBaseUri(baseUrl).
                build();

    }

    @Test
    @UseDataProvider("requestUserPayload")
    public void shouldCreateUserForPetStoreAndPrint(UserCreateForPetRequestModel requestUser){

        Response response = given().
                spec(requestSpec).contentType(ContentType.JSON).body(requestUser).
                when().
                post(userPath).
                then().assertThat().statusCode(200).and().extract().response();

        System.out.println("Details of the user created :" + response.asString());

    }




    // serialization - array of pet store users and deserialization to extract the response and print
    @Test
    @UseDataProvider("requestUserAllPayload")
    public void shouldCreateArrayOfUsersForPetStore(List userCreationArray){


        UserCreateForPetResponseModel userAllResponseBody =
                given().spec(requestSpec).
                        contentType(ContentType.JSON).body(userCreationArray).
                        when().
                        post(userAllPath).as(UserCreateForPetResponseModel.class);

        System.out.println(userAllResponseBody.toString());

        Assert.assertEquals(200,userAllResponseBody.getCode());
    }




    @Test
    @UseDataProvider("requestUserPayload")
    public void testUserCreated(UserCreateForPetRequestModel requestUser){

        String userName = requestUser.getUsername();

        System.out.println("User Name :" + userName);
        given().spec(requestSpec).when().get(userPath+"/"+userName).then().assertThat().statusCode(200);
    }


    @Test
    @UseDataProvider("requestUserPayload")
    public void testInvalidURL(UserCreateForPetRequestModel requestUser) {

        given().
                contentType(ContentType.JSON).
                body(requestUser).
                log().
                body().
                when().
                post("https://petstore.swagger.io/xyz/user").
                then().assertThat().statusCode(404);
    }

    @Test
    public void testInvalidJsonBody() {

        String jsonBody = "{\n" +
                "  \"id\": \"id\",\n" +
                "  \"username\": \"string\",\n" +
                "  \"firstName\": \"string\",\n" +
                "  \"lastName\": \"string\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"password\": \"string\",\n" +
                "  \"phone\": \"string\",\n" +
                "  \"userStatus\": \"userStatus\"\n" +
                "}";

        given().
                spec(requestSpec).contentType(ContentType.JSON).body(jsonBody).
                when().
                post(userPath).
                then().assertThat().statusCode(500);

    }
        @Test
        public void testInvalidContentType(){

            String jsonBodyInvalidContent ="{\n" +
                    "  \"id\": 701,\n" +
                    "  \"username\": \"abc\",\n" +
                    "  \"firstName\": \"abc\",\n" +
                    "  \"lastName\": \"abc\",\n" +
                    "  \"email\": \"abc\",\n" +
                    "  \"password\": \"abc\",\n" +
                    "  \"phone\": \"abc\",\n" +
                    "  \"userStatus\": 0\n" +
                    "}";

            given().
                    spec(requestSpec).contentType(ContentType.XML).body(jsonBodyInvalidContent).
                    when().
                    post(userPath).
                    then().assertThat().statusCode(400);

    }


}