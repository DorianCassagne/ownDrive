package me.dcal.owndrive.database.repository;


import me.dcal.owndrive.database.model.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


}
