package me.dcal.owndrive.database.repository;

import me.dcal.owndrive.database.model.Data;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data,Long> {
}
