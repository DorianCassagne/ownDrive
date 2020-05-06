package me.dcal.owndrive.server.controller;

import me.dcal.owndrive.server.model.DemoResource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class DemoControl {

    private AtomicLong counter = new AtomicLong();

    @GetMapping("/demo")
    public DemoResource getDemoResource() {
        return new DemoResource(3,"test");
    }
    @GetMapping("/file/getPublicFile/{file}")
    public FileSystemResource getPublicFile(@PathVariable("file") String filename) {
        return new FileSystemResource(new File("src/main/resources/file/public/"+filename));
    }

    @PostMapping("/file/savePublicFile/{file}")
    public String savePublicFile(@RequestParam("file") CommonsMultipartFile file) {
        String path="src/main/resources/file/public/";
//                session.getServletContext().getRealPath("/");

        System.out.println(path+" "+"filename.txt");
        try{
            byte barr[]=file.getBytes();

            BufferedOutputStream bout=new BufferedOutputStream(
                    new FileOutputStream(path+"/"+"filename.txt"));
            bout.write(barr);
            bout.flush();
            bout.close();

        }catch(Exception e){System.out.println(e);}
        return "okay";

    }
    //@RequestParam("image") String image


//    public ResponseEntity<Resource> download() throws IOException {
//        File file = new File("src/main/resources/file/public/text.txt");
//
//        HttpHeaders header = new HttpHeaders();
//        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.jpg");
//        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        header.add("Pragma", "no-cache");
//        header.add("Expires", "0");
//
//        Path path = Paths.get(file.getAbsolutePath());
//        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//
//        return ResponseEntity.ok()
//                .headers(header)
//                .contentLength(file.length())
//                .contentType(MediaType.parseMediaType("application/octet-stream"))
//                .body(resource);
//    }

}
