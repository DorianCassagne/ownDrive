package me.dcal.owndrive.database.model;

import me.dcal.owndrive.database.dto.UserDTO;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "USER")
@Table(name="user")

public class User {
    @Id
    private String username;

    @ManyToMany(mappedBy="mesUsers")
    Set<Data> data;

    public User(String username) {
        super();
        this.username = username;
        this.data = new HashSet<Data>();
    }

    public User(){}
    public User(UserDTO user) {
        super();
        this.username = user.getUsername();
        this.data = new HashSet<Data>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
