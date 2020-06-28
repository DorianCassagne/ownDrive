package me.dcal.owndrive.server.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class DemoControl {

    private AtomicLong counter = new AtomicLong();

    @GetMapping("/filename")
    public File[] filename() {
        File repertoire = new File("server/src/main/resources/file/public/");

        return repertoire.listFiles();
    }
    @GetMapping("/file/getPublicFile/{file}")
    public FileSystemResource getPublicFile(@PathVariable("file") String filename) {
        return new FileSystemResource(new File("server/src/main/resources/file/public/"+filename));
    }

    @PostMapping("/file/renamePublicFile/{filename}")
    public String renamePublicFile(@RequestParam("newfilename") String newfilename,@RequestParam("old") String old, @PathVariable("filename") String filename) {
        String path="server/src/main/resources/file/public/";
//                session.getServletContext().getRealPath("/");

        try{

            new File(path+"/"+old).renameTo(new File(path+"/"+newfilename));

        }catch(Exception e){System.out.println(e);}
        return "okay\n";

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
