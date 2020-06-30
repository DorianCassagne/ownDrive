package me.dcal.owndrive.database.repository;


import me.dcal.owndrive.database.model.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.Table;

@EntityScan
public interface UserRepository extends JpaRepository<User,String> {


}
