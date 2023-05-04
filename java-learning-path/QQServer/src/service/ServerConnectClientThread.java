package service;

import common.Message;
import common.MessageType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

//和客户端保持通信
public class ServerConnectClientThread extends Thread {
    //该线程持有socket
    private Socket socket;
    private String userId;

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    public Socket getSocket(){
        return socket;
    }

    @Override
    public void run() {
        while(true){
            try {
                System.out.println("服务端和客户端"+ userId +"保持通信中");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                if(message.getMesType().equals(MessageType.MESSAGE_GET_ONLINE_USERS)){
                    //客户端要用户列表
                    System.out.println(message.getSender()+"要在线用户列表");
                    String onlineUser = ManageClientThreads.getOnlineUser();
                    //构建message对象，返回给客户端
                    Message message2 = new Message();
                    message2.setMesType(MessageType.MESSAGE_RETURN_ONLINE_USERS);
                    message2.setContent(onlineUser);
                    message2.setReceiver(message.getSender());
                    //返回客户端
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message2);
                }else if(message.getMesType().equals(MessageType.MESSAGE_CLIENT_EXT)){
                    ManageClientThreads.removeThread(message.getSender());
                    socket.close();
                    break;
                } else if(message.getMesType().equals(MessageType.MESSAGE_COMMON_MES)) {
                    //写一个方法 判断receiver是否在线 不在线的话 将message对象存入到集合中
                    if(!OffLineService.isOnline(message.getReceiver())){
                        System.out.println("该用户不在线 将在登录后接受到消息");
                        OffLineService.addToOffLine(message);
                    }else {
                        //根据message获取receiverid和对应线程
                        ServerConnectClientThread serverConnectClientThread = ManageClientThreads.getServerConnectClientThread(message.getReceiver());
                        ObjectOutputStream oos =
                                new ObjectOutputStream(serverConnectClientThread.socket.getOutputStream());
                        oos.writeObject(message);
                    }
                } else if(message.getMesType().equals(MessageType.MESSAGE_TO_ALL)) {
                    //遍历所有的线程，排除自己
                    HashMap<String, ServerConnectClientThread> map = ManageClientThreads.getMap();
                    Iterator<String> iterator = map.keySet().iterator();
                    while(iterator.hasNext()){
                        //取出在线用户id
                        String onlineUserId = iterator.next();
                        if (!onlineUserId.equals(message.getSender())){ //排除自己
                            ObjectOutputStream oos =
                                    new ObjectOutputStream(map.get(onlineUserId).socket.getOutputStream());
                            oos.writeObject(message);
                        }
                    }
                } else if (message.getMesType().equals(MessageType.MESSAGE_FILE_MES)) {
                    //获取对应线程根据receiverid
                    ServerConnectClientThread serverConnectClientThread =
                            ManageClientThreads.getServerConnectClientThread(message.getReceiver());
                    ObjectOutputStream oos =
                            new ObjectOutputStream(serverConnectClientThread.socket.getOutputStream());
                    oos.writeObject(message);
                } else{
                    System.out.println("qita");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
