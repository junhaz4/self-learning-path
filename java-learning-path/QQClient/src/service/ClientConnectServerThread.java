package service;

import common.Message;
import common.MessageType;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientConnectServerThread extends Thread{
    //该线程持有socket
    private Socket socket;

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() { //需要在后台和服务器通信
        while(true){
            try {
                System.out.println("客户端线程等待服务器端发送消息");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //处理读入的对象
                Object o = ois.readObject();//如果服务器没有发送objec对象,线程会阻塞在这里
                if(o instanceof ArrayList){
                    ArrayList<Message> messageArrayList=(ArrayList<Message>)o;
                    System.out.println("以下是您的离线消息：⬇");
                    for (Message message: messageArrayList){
                        System.out.println(message.getSender()+": "+message.getContent()+" time:");
                    }
                }else {
                    Message message = (Message) o;//如果服务器没有发送，线程会在这里阻塞
                    //如果读取到的是服务端返回的在线用户列表
                    if (message.getMesType().equals(MessageType.MESSAGE_RETURN_ONLINE_USERS)) {
                        //取出在线列表信心
                        String[] onlineUsers = message.getContent().split(" ");
                        System.out.println("\n========当前在线用户列表========");
                        for (int i = 0; i < onlineUsers.length; i++) {
                            System.out.println("用户： " + onlineUsers[i]);
                        }
                    } else if (message.getMesType().equals(MessageType.MESSAGE_COMMON_MES)) {
                        System.out.println("\n" + message.getSender() + " 对 "
                                + message.getReceiver() + " 说: " + message.getContent());
                    } else if (message.getMesType().equals(MessageType.MESSAGE_TO_ALL)) {
                        System.out.println("\n" + message.getSender() + " 对大家说 " + message.getContent());
                    } else if (message.getMesType().equals(MessageType.MESSAGE_FILE_MES)) {
                        System.out.println("\n" + message.getSender() + " 给 " + message.getReceiver()
                                + " 发文件： " + message.getSrc() + " 到 " + message.getDest());
                        //取出message文件数组，通过文件输出流写到磁盘
                        FileOutputStream fileOutputStream = new FileOutputStream(message.getDest());
                        fileOutputStream.write(message.getFileBytes());
                        fileOutputStream.close();
                        System.out.println("\n 文件保存成功");
                    } else {
                        System.out.println("qita");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
