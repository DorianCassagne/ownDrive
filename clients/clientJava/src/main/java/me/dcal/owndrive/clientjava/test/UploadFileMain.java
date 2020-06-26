package me.dcal.owndrive.clientjava.test;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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
            urlconnection.setDoInput(true);
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
    
    public static void main(String[] args) {
        String sourceURL ="http://127.0.0.1:8888//upload//test.py";
        String targetDirectory = "/Users/gregoryarnal/Downloads/";
        //test3();
        System.out.println("e :" + download(sourceURL,targetDirectory ));
    }

}