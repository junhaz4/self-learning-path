package service;

import common.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class OffLineService {
    //key->receiverID value->list of messages
    private static ConcurrentHashMap<String, ArrayList> offlineMap=new ConcurrentHashMap();

    public static ConcurrentHashMap getOffLineMap(){
        return offlineMap;
    }

    public static void setOfflineMap(ConcurrentHashMap<String, ArrayList> offlineMap) {
        OffLineService.offlineMap = offlineMap;
    }

    //将离线消息存入到集合中
    public static void addToOffLine(Message message){
        String receiverId = message.getReceiver();
        if(!offlineMap.containsKey(receiverId)){
            ArrayList<Message> messages = new ArrayList<>();
            messages.add(message);
            offlineMap.put(receiverId,messages);
        }else{
            ArrayList arrayList = offlineMap.get(receiverId);
            arrayList.add(message);
        }
        System.out.println("离线消息已经存放在offlineMap中");
    }

    //编写方法判断receiver是否存在于offlineMap中,如果存在就获取对应receiver的socket将ArrayList中的所有内容发送
    public static void sendOffLineMessage(String receiverId, ConcurrentHashMap offlineMap){
        if(offlineMap.containsKey(receiverId)){
            try {
                ArrayList<Message> arrayList = (ArrayList<Message>) offlineMap.get(receiverId);
                OutputStream os = ManageClientThreads.getServerConnectClientThread(receiverId).getSocket().getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(arrayList);
                System.out.println("发送成功");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else{
            System.out.println("发送失败");
        }
    }

    //编写方法判断receiver是否在线
    public static boolean isOnline(String receiverId){
        HashMap<String, ServerConnectClientThread> map = ManageClientThreads.getMap();
        return map.containsKey(receiverId);
    }

    //将离线消息从offlineMap删除
    public static void deleteOffLineMes(String receiverId){
        ArrayList remove = offlineMap.remove(receiverId);
        System.out.println("删除消息成功"+remove);
    }
}
