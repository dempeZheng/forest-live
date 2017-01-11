/**
 * Created by Dempe on 2017/1/11 0011.
 */
public class Header {
    private short magic;
    private byte version;
    private Long uid;
    private Long topSid;
    private Long subSid;
    private byte platform;
    private int service;
    private byte type;//消息类型
    private byte extend;//扩展
}
