package netowrking.TCP_;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient02 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        OutputStream outputStream = socket.getOutputStream();
        //字符流
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello, server");
        //插入一个换行符，表示写入的内容，要求对方使用readline()读取
        bufferedWriter.newLine();
        //必须手动刷新，否则不会写入数据通道
        bufferedWriter.flush();
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);
        bufferedReader.close();
        bufferedWriter.close();
        socket.close();

    }
}
