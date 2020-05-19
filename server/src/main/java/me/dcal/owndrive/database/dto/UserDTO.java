package me.dcal.owndrive.database.dto;

public class UserDTO {

    String username;


    public UserDTO() {
    }

    public UserDTO(String id) {
        this.username = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + username +
                '}';
    }
}
