package netowrking;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class API_ {
    public static void main(String[] args) throws UnknownHostException {
        //获取本机INetAddress
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);

        //根据指定主机名，获取INetAddress对象
        InetAddress allName = InetAddress.getByName("Lawrences-MBP-2.attlocal.net");

        //根据域名，获取INetAddress对象
        InetAddress host = InetAddress.getByName("www.baidu.com");
        System.out.println(host);

        String hostAddress = host.getHostAddress();
        System.out.println(hostAddress);

        String hostName = host.getHostName();
        System.out.println(hostName);
    }
}