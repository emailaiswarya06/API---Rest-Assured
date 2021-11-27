package PetStore.API;

import PetStore.Model.UserCreateForPetRequestModel;
import PetStore.Model.UserCreateForPetResponseModel;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

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
        /*
      List<UserArrayCreateForPetStoreRequestModel> userAll=new ArrayList<>();

        //User1
        UserArrayCreateForPetStoreRequestModel userArrayRequest1 = new UserArrayCreateForPetStoreRequestModel();
        userArrayRequest1.setUserArray(Arrays.asList(111,"PeterForPets","Peter","Parker","peter.parker@gmail.com","PeterForPets","9016527676",1));

        //User 2
        UserArrayCreateForPetStoreRequestModel userArrayRequest2 = new UserArrayCreateForPetStoreRequestModel();
        userArrayRequest2.setUserArray(Arrays.asList(222,"AmyLovesCats","Amy","Williams","amy.williams@gmail.com","AmyLovesCats","9016011122",0));
*/
        List<UserCreateForPetRequestModel> userArray = new ArrayList<>();
        UserCreateForPetRequestModel user1 = new UserCreateForPetRequestModel();
        user1.setId(111);
        user1.setUsername("PeterForPets");
        user1.setFirstName("Peter");user1.setLastName("Parker");
        user1.setEmail("peter.parker@gmail.com");user1.setPassword("PeterForPets");
        user1.setPhone("9016527676");user1.setUserStatus(1);
        userArray.add(user1);


        UserCreateForPetRequestModel user2 = new UserCreateForPetRequestModel();
        user2.setId(222);
        user2.setUsername("AmyLovesCats");
        user2.setFirstName("Amy");user2.setLastName("Williams");
        user2.setEmail("amy.williams@gmail.com");user2.setPassword("AmyLovesCats");
        user2.setPhone("9016011122");user2.setUserStatus(0);

        userArray.add(user2);

        UserCreateForPetResponseModel userAllResponseBody =
                given().
                        contentType(ContentType.JSON).body(userArray).
                        when().
                        post("https://petstore.swagger.io/v2/user/createWithArray").as(UserCreateForPetResponseModel.class);

        System.out.println(userAllResponseBody.toString());

        Assert.assertEquals(200,userAllResponseBody.getCode());
    }
}
