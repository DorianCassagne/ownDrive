package me.dcal.owndrive.server.controller;

import me.dcal.owndrive.server.model.DemoResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class DemoControl {

    private AtomicLong counter = new AtomicLong();

    @GetMapping("/demo")
    public DemoResource getDemoResource() {
        return new DemoResource(counter.incrementAndGet(), "");
    }

}
