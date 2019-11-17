package lk.jwtsecurity.method2.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Document("User")
public class user {
    @Id
    private ObjectId _id;

    private String fName;
    private String username;
    private String password;
    private String roles;

    public user(ObjectId _id, String fName, String username, String password, String roles) {
        this._id = _id;
        this.fName = fName;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
