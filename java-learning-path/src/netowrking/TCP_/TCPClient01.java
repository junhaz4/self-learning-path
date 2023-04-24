package netowrking.TCP_;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
@SuppressWarnings({"all"})
public class TCPClient01 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        outputStream.write("hello server".getBytes());
        socket.shutdownOutput();
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len = inputStream.read(buffer)) != -1){
            System.out.println(new String(buffer,0,len));
        }
        outputStream.close();
        inputStream.close();
        socket.close();
    }
}
