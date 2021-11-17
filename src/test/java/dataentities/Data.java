package dataentities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "email", "firstName", "lastName", "avatar"})
public class Data {

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;

    public Data() {
        this.id = 7;
        this.email = "michael.lawson@reqres.in";
        this.firstName = "Michael";
        this.lastName = "Lawson";
        this.avatar = "https://reqres.in/img/faces/7-image.jpg";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(String firstName) { this.firstName = firstName; }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("last_name")
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) { this.avatar = avatar; }
}
