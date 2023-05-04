package service;

import java.util.HashMap;

//管理客户端和服务器端链接的线程
public class ManageClientServerThread {
    //将多个线程放入结合者，key-用户id
    private static HashMap<String,ClientConnectServerThread> map = new HashMap<>();

    public static void addClientConnectServerThread(String userId,ClientConnectServerThread clientConnectServerThread){
        map.put(userId, clientConnectServerThread);
    }

    public static ClientConnectServerThread getClientConnectServerThread(String userId){
        return map.get(userId);
    }
}
