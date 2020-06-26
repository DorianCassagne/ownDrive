package me.dcal.owndrive.clientjava;

import org.springframework.core.env.Environment;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class main {
    final String UPLOAD_URL = "http://127.0.0.1:8888/";

    public int mainMenu() {
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("\nMenu principal\n");
            System.out.print(" 1 - Mettre un fichier en ligne\n");
            System.out.print(" 2 - Consulter un fichier\n");
            System.out.print(" 3 - Télécharger un fichier\n");
            System.out.print(" 0 - Quitter\n");
            choice = sc.nextInt();
        } while (0 > choice || choice < choice);
        return choice;
    }


    public void sendFile(String path, String filename) {

        try {



            String input = "{\"path\":\""+path+"\",\"filename\":\""+filename+"\"}";

            System.out.println("input : " +input);
            URL url = new URL(UPLOAD_URL);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            System.out.println("conn : " +conn);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");



            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            os.close();
            conn.disconnect();
            System.out.print("conn resp code : " +conn.getResponseCode() +"\n");
            System.out.print("resp : " +HttpURLConnection.HTTP_CREATED +"\n");
            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void upload(){
        Scanner sc = new Scanner(System.in);
        File file;
        String pathsc;
        String path = "";
        String filename;
        do{
            System.out.println("\nVeuillez renseigner une url de fichier valide : \n");
            pathsc = sc.next();
            file = new File(pathsc);
        } while (!file.exists());

        String[] datafile = pathsc.split("/");
        for (int i = 0; i<datafile.length -1; i++){
            path += datafile[i]+"/";
        }
        filename = datafile[datafile.length-1];
        sendFile(path, filename);
    }

    public static void main(String[] args) {
        int choice;
        main main = new main();
        do{
            switch (choice = main.mainMenu()){
                case 1:
                    System.out.println("\nUpload\n");
                    main.upload();
                    break;
                case 2:
                    System.out.println("\nConsult\n");
                    break;
                case 3:
                    System.out.println("\nDownload\n");
                    break;
            }
        } while (choice != 0);
    }
}
