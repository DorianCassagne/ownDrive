package me.dcal.owndrive.server.controller;

import me.dcal.owndrive.server.service.DataServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ConnexionController {
    @Autowired
    DataServiceImplementation dataServiceImplementation;

    @PostMapping("/login")
    public String savePublicPictures(@RequestParam("user") String user, @PathVariable("password") String password) {

        return "okay\n";

    }

}
