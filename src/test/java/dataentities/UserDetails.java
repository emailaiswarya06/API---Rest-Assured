package dataentities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "job"})
public class UserDetails {

    private String name;
    private String job;

    public UserDetails() {
        this.name = "Sample";
        this.job = "Sample";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

}
