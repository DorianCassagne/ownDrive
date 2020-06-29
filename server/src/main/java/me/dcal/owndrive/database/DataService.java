package me.dcal.owndrive.database;

import me.dcal.owndrive.database.dto.DataDTO;
import me.dcal.owndrive.database.dto.UserDTO;

public interface DataService{
    UserDTO connexion(String login, String password);
    UserDTO users();
    UserDTO findUser(String username);
    void addUsers(Long idData, long idUser);
    UserDTO newUsers(String username, String password);
    DataDTO createData(long id, int type, String path);
    DataDTO findData(long id);
    DataDTO findDatasByUserId(long id);
    boolean isConnected();
    UserDTO getUserConnected();
    void disconnectUser();
}
