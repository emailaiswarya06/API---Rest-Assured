package dataentities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"data", "support"})
public class UserInfo {
    private Data data;
    private Support support;

    public UserInfo() {

        Data data = new Data();
        this.data = data;

        Support support = new Support();
        this.support = support;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }
}
