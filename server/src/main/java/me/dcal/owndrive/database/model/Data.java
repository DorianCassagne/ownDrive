package me.dcal.owndrive.database.model;


import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;
import java.util.Collection;



@Entity(name = "data")
@Table(name="data")
public class Data {
    @Id
    private String path;
    private String type;
    private String username;

    @ManyToMany
    Collection<User>mesUsers;

    public Data(String type, String path, String user) {
        super();
        this.type = type;
        this.path = path;
        this.username = user;
    }
    public Data(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

}
