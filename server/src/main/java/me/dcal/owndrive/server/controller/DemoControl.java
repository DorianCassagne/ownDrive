package me.dcal.owndrive.server.controller;

import me.dcal.owndrive.server.model.DemoResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoControl {

    @GetMapping("/demo")
    public DemoResource getDemoResource() {

    }

}
