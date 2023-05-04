package common;

public interface MessageType {
    String MESSAGE_LOGIN_SUCCEED = "1";
    String MESSAGE_LOGIN_FAILED = "2";
    String MESSAGE_COMMON_MES = "3"; //普通信息包
    String MESSAGE_GET_ONLINE_USERS = "4"; //要求返回在线用户列表
    String MESSAGE_RETURN_ONLINE_USERS = "5"; //返回在线用户列表
    String MESSAGE_CLIENT_EXT = "6"; //客户端请求退出
    String MESSAGE_TO_ALL = "7"; //群发消息
    String MESSAGE_FILE_MES = "8";
}
