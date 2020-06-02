package me.dcal.owndrive.database;

import me.dcal.owndrive.database.dto.DataDTO;
import me.dcal.owndrive.database.dto.UserDTO;
import me.dcal.owndrive.database.model.User;
import me.dcal.owndrive.database.repository.DataRepository;
import me.dcal.owndrive.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
@ComponentScan(basePackages ={"me.dcal.owndrive.database.repository"})
public class DataServiceImplementation implements DataService {

    @Autowired(required=true)
    DataRepository dataRepository;

    @Autowired(required=true)
    UserRepository userRepository;

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
    public UserDTO newUsers(String id) {
        UserDTO userDTO = new UserDTO(id);
        User user = new User(id);
        userRepository.save(user);
        return userDTO;
    }

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
}
