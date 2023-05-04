package service;

import common.Message;
import common.MessageType;

import java.io.*;

public class FileClientService {
    /**
     *
     * @param src 来源
     * @param dest 目的地
     * @param senderId
     * @param receiverId
     */
    public void sendFile(String src, String dest, String senderId, String receiverId){
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_FILE_MES);
        message.setSender(senderId);
        message.setReceiver(receiverId);
        message.setDest(dest);
        message.setSrc(src);
        //读取文件
        FileInputStream fileInputStream = null;
        byte[] fileBytes = new byte[(int)new File(src).length()];

        try {
            fileInputStream = new FileInputStream(src);
            fileInputStream.read(fileBytes);
            //将字节数组设置到message对象
            message.setFileBytes(fileBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("\n" + senderId +" 给 "+receiverId+" 发送文件: " +src +" 到对方路径 "+dest);
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(ManageClientServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
