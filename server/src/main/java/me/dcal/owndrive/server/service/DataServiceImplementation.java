package me.dcal.owndrive.server.service;

import me.dcal.owndrive.database.dto.DataDTO;
import me.dcal.owndrive.database.dto.UserDTO;
import me.dcal.owndrive.database.model.User;
import me.dcal.owndrive.database.repository.DataRepository;
import me.dcal.owndrive.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional
@ComponentScan(basePackages ={"me.dcal.owndrive.database.repository"})
public class DataServiceImplementation implements DataService {

    private AtomicReference<UserDTO> actualUser = new AtomicReference<>();

    @Autowired
    DataRepository dataRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO connexion(String login, String password) {
        Optional<User> optionalUser = userRepository.findById(login);
        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)){
            UserDTO u = userToUserDTO(optionalUser.get());
            actualUser.set(u);
            return u;
        }
        return null;
    }


    @Override
    public UserDTO users() {
        return null;
    }


    @Override
    public UserDTO findUser(String username) {
        return null;
    }

    @Override
    public void addUsers(Long idData, long idUser){
    }

    @Override
    public UserDTO newUsers(String username, String password) {
        User user = new User(username);
        user.setPassword(password);
        userRepository.save(user);
        return userToUserDTO(user);
    }

    /*@Override
    public UserDTO newUsers(String id) {
        UserDTO userDTO = new UserDTO(id);
        User user = new User(id);
        userRepository.save(user);
        return userDTO;
    }
*/
    @Override
    public DataDTO createData(long id, int type, String path) {
        return null;
    }

    @Override
    public DataDTO findData(long id) {
        return null;
    }

    @Override
    public DataDTO findDatasByUserId(long id) {
        return null;
    }

    @Override
    public boolean isConnected() {
        return !this.actualUser.get().equals(null);
    }

    @Override
    public UserDTO getUserConnected() {
        return this.actualUser.get();
    }

    @Override
    public void disconnectUser() {
        this.actualUser.set(null);
    }

    private UserDTO userToUserDTO(User u){
        UserDTO ud = new UserDTO();
        ud.setUsername(u.getUsername());
        return ud;

    }
}
