package netowrking.UDP_;

import java.io.IOException;
import java.net.*;

public class UDPSender {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9998);
        byte[] data = "hello".getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(),9999);
        socket.send(packet);

        byte[] buf = new byte[1024];
        //用packet接受数据
        packet = new DatagramPacket(buf,buf.length);
        socket.receive(packet);
        //packet拆包，取出数据
        int length = packet.getLength();
        data = packet.getData();
        String s = new String(data,0,length);
        System.out.println(s);
        socket.close();
    }
}
