package PetStore.Model;

import java.util.ArrayList;
import java.util.List;

public class UserArrayCreateForPetStoreRequestModel {

    private List<Object> userArray = new ArrayList<>();

    public List<Object> getUserArray() {
        return userArray;
    }

    public void setUserArray(List<Object> userArray) {
        this.userArray.addAll(userArray);


    }
}
