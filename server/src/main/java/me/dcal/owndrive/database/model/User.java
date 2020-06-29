package me.dcal.owndrive.database.model;

import me.dcal.owndrive.database.dto.UserDTO;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "user")
@Table(name="user")

public class User {
    @Id
    private String username;
    @Column
    private String password;

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

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
