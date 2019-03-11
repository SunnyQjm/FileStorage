package FileStorage.communicate_with_server;

import properties.StorageNode;

import java.io.IOException;

/**
 * 该类表示一个与服务器通信的任务，负责向服务器发送一个心跳包
 * Created by Sunny on 2017/7/7 0007.
 */
public class CommunicateWithServerRunnable implements Runnable {
    private CommunicateWithServerStrategy cwss;
    private String ip;
    private int port;
    //心跳包间隔2s发送
    private static final int heartPacketSpan = 2000;

    public CommunicateWithServerRunnable(CommunicateWithServerStrategy cwss, String ip, int port) {
        this.cwss = cwss;
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        while (true){
            try {
                //更新或注册
                cwss.registerOrUpdate(ip, port);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(StorageNode.nodeName + ": 向服务器发送心跳包失败，请检查服务器是否已正常启动");
            }
            try {
                Thread.sleep(heartPacketSpan);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
