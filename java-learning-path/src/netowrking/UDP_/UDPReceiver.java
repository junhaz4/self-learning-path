package netowrking.UDP_;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPReceiver {
    public static void main(String[] args) throws IOException {
        //在9999接受数据
        DatagramSocket socket = new DatagramSocket(9999);
        byte[] buf = new byte[1024];
        //用packet接受数据
        DatagramPacket packet = new DatagramPacket(buf,buf.length);
        socket.receive(packet);
        //packet拆包，取出数据
        int length = packet.getLength();
        byte[] data = packet.getData();
        String s = new String(data,0,length);
        System.out.println(s);

        data = "hello".getBytes();
        packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(),9998);
        socket.send(packet);
        socket.close();
    }
}
