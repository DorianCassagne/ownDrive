package me.dcal.owndrive.database.dto;

import me.dcal.owndrive.database.model.User;

public class DataDTO {
    Long id;
    int type;
    String path;
    User user;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public DataDTO(Long id, int type, String path, User user) {
        this.id = id;
        this.type = type;
        this.path = path;
        this.user = user;
    }

    public DataDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DataDTO{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
