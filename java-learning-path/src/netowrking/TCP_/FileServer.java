package netowrking.TCP_;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] b = new byte[1024];
        int len = 0;
        String fileName= "";
        while((len=inputStream.read(b))!=-1){
            fileName += new String(b,0,len);
        }
        System.out.println(fileName);
        String name = "";
        if("hello".equals(fileName)){
            name = "hello.txt";
        }else{
            name = "none";
        }
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(name));
    }
}
