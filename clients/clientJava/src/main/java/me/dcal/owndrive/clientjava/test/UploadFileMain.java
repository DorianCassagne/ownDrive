package me.dcal.owndrive.clientjava.test;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.*;
import org.springframework.http.HttpHeaders;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class UploadFileMain {

    public static void test2(){
        URLConnection urlconnection = null;
        try{
            File file = new File("/Users/gregoryarnal/Desktop/test.py");
            URL url = new URL("http://127.0.0.1:8888/upload/test.txt");
            urlconnection = url.openConnection();
            urlconnection.setDoOutput(true);
            urlconnection.setDoInput(true);
            urlconnection.setUseCaches(false);
            if (urlconnection instanceof HttpURLConnection) {
                ((HttpURLConnection) urlconnection).setRequestMethod("POST");
                ((HttpURLConnection) urlconnection).setRequestProperty("Content-type", "text/plain");
                ((HttpURLConnection) urlconnection).connect();
            }
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new OutputStreamWriter(urlconnection.getOutputStream()));
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    for (String line; (line = reader.readLine()) != null;) {
                        writer.write(line);
                    }

                } finally {
                    if (reader != null) try { reader.close(); } catch (IOException logOrIgnore) {}
                }
                writer.println("-- --");
                writer.flush();
            } finally {
                if (writer != null) writer.close();
            }

// Connection is lazily executed whenever you request any status.
            int responseCode = ((HttpURLConnection) urlconnection).getResponseCode();
            System.out.println(responseCode); // Should be 200
        }catch (IOException e) {
            System.out.println("e :" + e);
            e.printStackTrace();
        }

    }
    public static void test1(){
        URLConnection urlconnection = null;
        try {
            File file = new File("/Users/gregoryarnal/Desktop/test.py");
            URL url = new URL("http://127.0.0.1:8888/upload//test.txt");
            urlconnection = url.openConnection();
            urlconnection.setDoOutput(true);
            urlconnection.setUseCaches(false);
            if (urlconnection instanceof HttpURLConnection) {
                ((HttpURLConnection) urlconnection).setRequestMethod("POST");
                ((HttpURLConnection) urlconnection).setRequestProperty("Content-type", "text/plain");
                ((HttpURLConnection) urlconnection).connect();
            }

            //BufferedOutputStream destination = new BufferedOutputStream(urlconnection.getOutputStream());
            DataOutputStream destination = new DataOutputStream(
                    urlconnection.getOutputStream());
            BufferedInputStream source = new BufferedInputStream(new FileInputStream(file));
            int i;

            // read byte by byte until end of stream

            while ((i = source.read()) > -1) {
                System.out.println("lettre : " + (char)i);
                destination.writeBytes((char)i+"");
            }
            destination.flush();
            source.close();
            destination.close();

            System.out.println("response : " + ((HttpURLConnection) urlconnection).getResponseMessage());
        } catch (Exception e) {
            System.out.println("e :" + e);
            e.printStackTrace();
        }
        try {

            InputStream inputStream;
            int responseCode = ((HttpURLConnection) urlconnection).getResponseCode();
            if ((responseCode >= 200) && (responseCode <= 202)) {
                inputStream = ((HttpURLConnection) urlconnection).getInputStream();
                int j;
                while ((j = inputStream.read()) > -1) {
                    System.out.println("j :" + j);
                }

            } else {
                inputStream = ((HttpURLConnection) urlconnection).getErrorStream();
            }
            ((HttpURLConnection) urlconnection).disconnect();

        } catch (IOException e) {
            System.out.println("e :" + e);
            e.printStackTrace();
        }
    }
    public static void test3(){
        URLConnection urlconnectionsource = null;
        URLConnection urlconnectiondestination = null;
        try {
            File file = new File("/Users/gregoryarnal/Desktop/test.py");
            URL destination = new URL("http://127.0.0.1:8888//upload//test.txt");
            URL source = new URL("http://127.0.0.1:8888//upload//test.py");
            urlconnectionsource = source.openConnection();
            urlconnectiondestination = destination.openConnection();
            urlconnectiondestination.setDoOutput(true);
            urlconnectionsource.setDoInput(true);
            if (urlconnectionsource instanceof HttpURLConnection) {
                ((HttpURLConnection) urlconnectionsource).connect();
            }
            if (urlconnectiondestination instanceof HttpURLConnection) {
                ((HttpURLConnection) urlconnectiondestination).connect();
            }
            InputStream source_stream =
                    urlconnectionsource.getInputStream();
            OutputStream destination_stream =
                    urlconnectiondestination.getOutputStream();


            copy(source_stream, destination_stream);
            destination_stream.flush();
            source_stream.close();
            destination_stream.close();
            ((HttpURLConnection) urlconnectionsource).disconnect();
            ((HttpURLConnection) urlconnectiondestination).disconnect();
        }catch (IOException e) {
            System.out.println("e :" + e);
            e.printStackTrace();
        }
    }


    protected static long copy(InputStream input, OutputStream output)
            throws IOException {
        byte[] buffer = new byte[12288]; // 12K
        long count = 0L;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    public static Path download(String sourceURL, String targetDirectory){
        try{
            //String sourceURL ="http://127.0.0.1:8888//upload//test.py";
            //String targetDirectory = "/Users/gregoryarnal/Downloads/";
            URL url = new URL(sourceURL);
            String fileName = sourceURL.substring(sourceURL.lastIndexOf('/') + 1, sourceURL.length());
            Path targetPath = new File(targetDirectory + File.separator + fileName).toPath();
            Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            return targetPath;
        } catch (IOException e) {
            System.out.println("e :" + e);
            e.printStackTrace();
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

        return sb.toString();
    }



    public static Path upload(String targetDirectory, String sourceURL){
        try{
            //String sourceURL ="http://127.0.0.1:8888//upload//test.py";
            //String targetDirectory = "/Users/gregoryarnal/Downloads/";
            URL url = new URL(targetDirectory);
            String fileName = sourceURL.substring(sourceURL.lastIndexOf('/') + 1, sourceURL.length());
            Path sourcePath = new File(targetDirectory + File.separator + fileName).toPath();
            Files.copy(url.openStream(), sourcePath, StandardCopyOption.REPLACE_EXISTING);

            return sourcePath;
        } catch (IOException e) {
            System.out.println("e :" + e);
            e.printStackTrace();
        }
        return null;
    }

    public static void testRest() throws IOException {
        URL url = new URL("http://localhost:8888//file/savePublicFile/");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "application/json");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP Error code : "
                    + conn.getResponseCode());
        }
        InputStreamReader in = new InputStreamReader(conn.getInputStream());
        BufferedReader br = new BufferedReader(in);
        String output;

        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }
        conn.disconnect();
    }

    public static void test666(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(new File("/Users/gregoryarnal/Downloads/test.txt")));
        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        String serverUrl = "http://127.0.0.1:8080//file/savePublicFile/test.txt";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate
                .postForEntity(serverUrl, requestEntity, String.class);
    }
    public static void main(String[] args) throws IOException {
        String  sourceURL ="http://127.0.0.1:8080//file/getPublicFile/macron.txt";
        String targetDirectory = "/Users/gregoryarnal/Downloads";

        String  sourceURLup ="/Users/gregoryarnal/Downloads/test.txt";
        String targetDirectoryup = "http://127.0.0.1:8888//upload/";
        //testRest();
        test666();
        //System.out.println("download :" + download(sourceURL,targetDirectory ));
        //System.out.println("upload :" + upload(targetDirectoryup, sourceURLup ));

        System.out.println("\nOutput: \n" + callURL("http://127.0.0.1:8080/filename"));

    }

}