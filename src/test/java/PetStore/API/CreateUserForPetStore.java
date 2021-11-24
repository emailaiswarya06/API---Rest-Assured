package PetStore.API;

import PetStore.Model.UserArrayCreateForPetStoreRequestModel;
import PetStore.Model.UserCreateForPetRequestModel;
import PetStore.Model.UserCreateForPetResponseModel;
import Serialization.UserCreateRequestModel;
import Serialization.UserCreateResponseModel;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CreateUserForPetStore {

    @Test
    public void shouldCreateUserForPetStore(){

//        String userRequestBody ="{\n" +
//                "  \"id\": 101,\n" +
//                "  \"username\": \"jackson\",\n" +
//                "  \"firstName\": \"Jackson\",\n" +
//                "  \"lastName\": \"Collins\",\n" +
//                "  \"email\": \"jakson.collins@gmail.com\",\n" +
//                "  \"password\": \"petLover\",\n" +
//                "  \"phone\": \"901601601\",\n" +
//                "  \"userStatus\": 0\n" +
//                "}";

        UserCreateForPetRequestModel userPetStoreRequestBody = new UserCreateForPetRequestModel();
        userPetStoreRequestBody.setId(101);
        userPetStoreRequestBody.setUsername("jacksonThePetLover");
        userPetStoreRequestBody.setFirstName("Jackson");userPetStoreRequestBody.setLastName("Collins");
        userPetStoreRequestBody.setEmail("jakson.collins@gmail.com");userPetStoreRequestBody.setPassword("petLover");
        userPetStoreRequestBody.setPhone("901601601");userPetStoreRequestBody.setUserStatus(1);

        given().
                contentType(ContentType.JSON).body(userPetStoreRequestBody).
                when().
                post("https://petstore.swagger.io/v2/user").
                then().
                assertThat().statusCode(200);

    }
// serialization - array of pet store users and deserialization to extract the response and print
    @Test
    public void shouldCreateArrayOfUsersForPetStore(){
        List<UserArrayCreateForPetStoreRequestModel> userAll=new ArrayList<>();
        //User1
        UserArrayCreateForPetStoreRequestModel userArrayRequest1 = new UserArrayCreateForPetStoreRequestModel();
        userArrayRequest1.setUserArray(Arrays.asList(111,"PeterForPets","Peter","Parker","peter.parker@gmail.com","PeterForPets","9016527676",1));

        //User 2
        UserArrayCreateForPetStoreRequestModel userArrayRequest2 = new UserArrayCreateForPetStoreRequestModel();
        userArrayRequest2.setUserArray(Arrays.asList(222,"AmyLovesCats","Amy","Williams","amy.williams@gmail.com","AmyLovesCats","9016011122",0));

        UserCreateForPetResponseModel userAllResponseBody =
        given().
                contentType(ContentType.JSON).body(userAll).
                when().
                post("https://petstore.swagger.io/v2/user/createWithArray").as(UserCreateForPetResponseModel.class);
//                then().
//                assertThat().statusCode(200);

        System.out.println(userAllResponseBody);

        Assert.assertEquals(200,userAllResponseBody.getCode());
    }
}
