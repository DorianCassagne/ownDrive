package me.dcal.owndrive.database;

import me.dcal.owndrive.server.service.DataService;
import me.dcal.owndrive.server.service.DataServiceImplementation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class DataServiceTest {


    @Configuration
    @EnableAutoConfiguration
    @Import(DataServiceImplementation.class)
    static class ConfigDuTest {

    }

    @Autowired
    DataService dataService;


    @Test
    void findUser() {
    }

    @Test
    void addUsers() {
    }
    

    @Test
    void newUsers() {

    }

    @Test
    void createData() {
    }

    @Test
    void findData() {
    }

    @Test
    void findDatasByUserUsername() {

    }
}