package service;

import common.Message;
import common.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

//和服务端沟通,提供和消息相关方法
public class MessageClientService {
    /**
     *
     * @param content 内容
     * @param senderId 发送者
     * @param receiverId 接收者
     */
    public void sendMessage(String content, String senderId, String receiverId){
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_COMMON_MES);
        message.setSender(senderId);
        message.setReceiver(receiverId);
        message.setContent(content);
        message.setSendTime(new Date().toString());
        System.out.println(senderId + "对" + receiverId + "说" +content);
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(ManageClientServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param content 内容
     * @param senderId 发送者
     */
    public void sendMessageAll(String content, String senderId){
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_TO_ALL);//群发
        message.setSender(senderId);
        message.setContent(content);
        message.setSendTime(new Date().toString());
        System.out.println(senderId + "对大家说" +content);
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(ManageClientServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
