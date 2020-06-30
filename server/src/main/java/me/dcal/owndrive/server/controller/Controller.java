package me.dcal.owndrive.server.controller;

import me.dcal.owndrive.server.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class Controller {

    private AtomicLong counter = new AtomicLong();
    @Autowired
    DataService dataService;

    @PostMapping("")

    @GetMapping("/filename")
    public File[] filename() {
        File repertoire = new File("server/src/main/resources/file/public/");

        return repertoire.listFiles();
    }

    @GetMapping("/fileList")
    public File[] fileList() {
        if (!dataService.isConnected())
            return null;
        File repertoire = new File("server/src/main/resources/file/"+dataService.getUserConnected().getUsername() + "/");

        return repertoire.listFiles();
    }

    @GetMapping("/file/getPublicFile/{file}")
    public FileSystemResource getPublicFile(@PathVariable("file") String filename) {
        return new FileSystemResource(new File("server/src/main/resources/file/public/"+filename));
    }
    @GetMapping("/user/connexion")
    public String connectUser(String user, String password, Model model){
        dataService.connexion(user,password);
        if(dataService.isConnected())
            return "ok";
        else return "ko";
    }

    @GetMapping("/user/isconnected")
    public String testIfUserConnected(){

        if(dataService.isConnected())
            return "ok";
        else return "ko";
    }

    @GetMapping("/user/getUsername")
    public String getUsername(){
        if (dataService.isConnected())
            return dataService.getUserConnected().getUsername();
        return "";
    }

    @GetMapping("/user/disconnect")
    public void disconnectUser(){
        dataService.disconnectUser();/*
        return "done";*/
    }

    @PostMapping("/file/saveFile/{filename}")
    public String saveFile(@RequestParam("file") MultipartFile file, @PathVariable("filename") String filename) {
        if (!dataService.isConnected())
            return "ko";
        String path="server/src/main/resources/file/"+dataService.getUserConnected().getUsername() + "/";
//                session.getServletContext().getRealPath("/");

        try{
            byte barr[]=file.getBytes();

            BufferedOutputStream bout=new BufferedOutputStream(
                    new FileOutputStream(path+"/"+filename));
            bout.write(barr);
            bout.flush();
            bout.close();

        }catch(Exception e){System.out.println(e);}
        return "ok\n";

    }

//    @GetMapping("/file/deletePublicFile/{file}")

    @PostMapping("/file/renamePublicFile/{filename}")
    public String renamePublicFile(@RequestParam("newfilename") String newfilename,@RequestParam("old") String old, @PathVariable("filename") String filename) {
        String path="server/src/main/resources/file/public/";
        try{

            new File(path+"/"+old).renameTo(new File(path+"/"+newfilename));

        }catch(Exception e){System.out.println(e);}
        return "okay\n";

    }

    @GetMapping("/file/getFile/{file}")
    public FileSystemResource getFile(@PathVariable("file") String filename) {
        if (!dataService.isConnected())
            return null;
        return new FileSystemResource(new File("server/src/main/resources/file/"+ dataService.getUserConnected().getUsername() + "/"+filename));
    }

    @PostMapping("/file/savePublicFile/{filename}")
    public String savePublicFile(@RequestParam("file") MultipartFile file, @PathVariable("filename") String filename) {
        String path="server/src/main/resources/file/public/";
//                session.getServletContext().getRealPath("/");

        try{
            byte barr[]=file.getBytes();

            BufferedOutputStream bout=new BufferedOutputStream(
                    new FileOutputStream(path+"/"+filename));
            bout.write(barr);
            bout.flush();
            bout.close();

        }catch(Exception e){System.out.println(e);}
        return "okay\n";

    }

    @PostMapping("/file/savePublicPictures/{filename}")
    public String savePublicPictures(@RequestParam("file") MultipartFile file, @PathVariable("filename") String filename) {
        String path="server/src/main/resources/image/";
//                session.getServletContext().getRealPath("/");

        try{
            byte barr[]=file.getBytes();

            BufferedOutputStream bout=new BufferedOutputStream(
                    new FileOutputStream(path+"/"+filename));
            bout.write(barr);
            bout.flush();
            bout.close();

        }catch(Exception e){System.out.println(e);}
        return "okay\n";

    }

    @GetMapping("/file/getPublicPictures/{file}")
    public FileSystemResource getPublicPictures(@PathVariable("file") String filename) {
        return new FileSystemResource(new File("server/src/main/resources/image/"+filename));
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
