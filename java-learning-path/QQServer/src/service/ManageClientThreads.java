package service;

import java.util.HashMap;
import java.util.Iterator;

//管理和客户端通信的线程
public class ManageClientThreads {
    private static HashMap<String,ServerConnectClientThread> map = new HashMap<>();

    public static HashMap<String,ServerConnectClientThread> getMap(){
        return map;
    }

    public static void addClientThread(String userid, ServerConnectClientThread serverConnectClientThread){
        map.put(userid, serverConnectClientThread);
    }

    public static ServerConnectClientThread getServerConnectClientThread(String userid){
        return map.get(userid);
    }

    //返回在线用户列表
    public static String getOnlineUser(){
        Iterator<String> iterator = map.keySet().iterator();
        String onlineUserList = "";
        while(iterator.hasNext()){
            onlineUserList += iterator.next().toString() + " ";
        }
        return onlineUserList;
    }

    public static void removeThread(String userId){
        map.remove(userId);
    }
}
