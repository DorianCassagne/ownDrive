package me.dcal.owndrive.clientjava.test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;

import static org.apache.coyote.http11.Constants.CRLF;

public class test {

    public static void test() throws IOException {
        URLConnection connection = null;
        // main method
        URL url = new URL("http://127.0.0.1:8888//upload//test.txt");
        connection =  url.openConnection();
        connection.setDoOutput(true); // triggers "POST"
// connection.setDoInput(true); // only if needed
        connection.setUseCaches(false); // dunno
        final String boundary = Long.toHexString(System.currentTimeMillis());
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary="
                + boundary);
        DataOutputStream output = new DataOutputStream(connection.getOutputStream());
        try {
            // image must be a File instance
            File image = new File("/Users/gregoryarnal/Desktop/IMG_0060.jpeg");
            flushMultiPartData(image, output, boundary);
        } catch (
                IOException e) {
            System.out.println("IOException in flushMultiPartData : " + e);
            return;
        }
    }

    private static void flushMultiPartData(File file, OutputStream serverOutputStream,
                                    String boundary) throws FileNotFoundException, IOException {
        // SEE https://stackoverflow.com/a/2793153/281545
        PrintWriter writer = null;
        try {
            // true = autoFlush, important!
            writer = new PrintWriter(new OutputStreamWriter(serverOutputStream), true);
            appendBinary(file, boundary, writer, serverOutputStream);
            // End of multipart/form-data.
            writer.append("--" + boundary + "--").append(CRLF);
        } finally {
            if (writer != null) writer.close();
        }
    }

    private static void appendBinary(File file, String boundary, PrintWriter writer,
                              OutputStream output) throws FileNotFoundException, IOException {
        // Send binary file.
        writer.append("--" + boundary).append(CRLF);
        writer.append(
                "Content-Disposition: form-data; name=\"binaryFile\"; filename=\""
                        + file.getName() + "\"").append(CRLF);
        writer.append("Content-Type: "
                +  URLConnection.guessContentTypeFromName(file.getName()))
                ;
        writer.append("Content-Transfer-Encoding: binary").append(CRLF);
        writer.append(CRLF).flush();
        InputStream input = null;
        try {
            input = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            for (int length = 0; (length = input.read(buffer)) > 0;) {
                output.write(buffer, 0, length);
            }
            output.flush(); // Important! Output cannot be closed. Close of
            // writer will close output as well.
        } finally {
            if (input != null) try {
                input.close();
            } catch (IOException logOrIgnore) {}
        }
        writer.append(CRLF).flush(); // CRLF is important! It indicates end of
        // binary boundary.
    }


    public static void main(String[] args) throws IOException {
        test();
    }
}
