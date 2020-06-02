package me.dcal.owndrive.database;

import me.dcal.owndrive.database.dto.DataDTO;
import me.dcal.owndrive.database.dto.UserDTO;

import java.util.List;

public interface DataService {
    UserDTO users();
    UserDTO findUser(String username);
    void addUsers(Long idData, long idUser);
    UserDTO newUsers(String username);
    DataDTO createData(long id, int type, String path);
    DataDTO findData(long id);
    DataDTO findDatasByUserId(long id);
}
