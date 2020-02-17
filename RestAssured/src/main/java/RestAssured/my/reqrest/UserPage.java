package RestAssured.my.reqrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPage {

    User[] data;

    public User[] getData() {
        return data;
    }

    public void setData(User[] data) {
        this.data = data;
    }
}
