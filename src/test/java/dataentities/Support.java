package dataentities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"url", "text"})
public class Support {

    private String url;
    private String text;

    public Support() {
        this.url = "https://reqres.in/#support-heading";
        this.text = "To keep ReqRes free, contributions towards server costs are appreciated!";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
