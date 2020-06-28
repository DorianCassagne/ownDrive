package me.dcal.owndrive.clientjava;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class client {
    final String UPLOAD_URL = "http://127.0.0.1:8888/";


    public void upload(File file){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        //body.add("file", new FileSystemResource(new File("/Users/gregoryarnal/Downloads/palmiers.jpeg")));
        body.add("file", new FileSystemResource(file));

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);
        String fileName = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(File.separatorChar) + 1, file.getAbsolutePath().length());
        String serverUrl = "http://127.0.0.1:8080//file/savePublicFile/" + fileName;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate
                .postForEntity(serverUrl, requestEntity, String.class);

    }

    public static Path download(String filePath){
        try{
            String  sourceURL ="http://127.0.0.1:8080/file/getPublicFile/" + filePath;
            String targetDirectory = System.getProperty("user.home") + File.separator + "Downloads";
            URL url = new URL(sourceURL);
            String fileName = sourceURL.substring(sourceURL.lastIndexOf(File.separatorChar) + 1, sourceURL.length());
            System.out.println(fileName);
            Path targetPath = new File(targetDirectory + File.separator + fileName).toPath();
            Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            return targetPath;
        } catch (IOException e) {
            System.out.println("e :" + e);
            e.printStackTrace();
        }
        return null;
    }

    public static void consult(){
        URLConnection urlconnection = null;
        try{
            String  sourceURL ="http://127.0.0.1:8080/file/getPublicFile/macron.txt";
            URL url = new URL(sourceURL);
            urlconnection = url.openConnection();
            urlconnection.setDoInput(true);
            urlconnection.setUseCaches(false);
            if (urlconnection instanceof HttpURLConnection) {
                ((HttpURLConnection) urlconnection).setRequestMethod("GET");
                ((HttpURLConnection) urlconnection).setRequestProperty("Content-type", "text/plain");
                ((HttpURLConnection) urlconnection).connect();
            }

            InputStream source_stream =
                    urlconnection.getInputStream();
            int n;
            while (-1 != (n = source_stream.read())) {
                System.out.print((char)n);
            }

        } catch (IOException e) {
            System.out.println("e :" + e);
            e.printStackTrace();
        }

    }

    public static File[] get_file() throws IOException {
        URLConnection urlconnection = null;
        String  URL ="http://127.0.0.1:8080/getfilename";
        URL url = new URL(URL);
        urlconnection = url.openConnection();

        if (urlconnection instanceof HttpURLConnection) {
            ((HttpURLConnection) urlconnection).setRequestMethod("GET");
            ((HttpURLConnection) urlconnection).setRequestProperty("Content-type", "text/plain");
            ((HttpURLConnection) urlconnection).connect();
        }
        return null;
    }


    public static String callURL(String myURL) {
        System.out.println("Requeted URL:" + myURL);
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;
        try {
            URL url = new URL(myURL);
            urlConn = url.openConnection();
            if (urlConn != null)
                urlConn.setReadTimeout(60 * 1000);
            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(),
                        Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception while calling URL:"+ myURL, e);
        }
        System.out.println("File:" + sb.toString());
        System.out.println("File:" + sb.getClass());
        return sb.toString();
    }

}
