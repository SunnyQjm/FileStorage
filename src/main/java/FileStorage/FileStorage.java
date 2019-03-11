package FileStorage;

import FileStorage.communicate_with_server.CommunicateWithServerStrategyImpl;
import FileStorage.communicate_with_server.CommunicateWithServerThreadSupport;
import FileStorage.trans_with_client.TransWithClientServer;
import FileStorage.trans_with_client.TransWithClientThreadSupport;
import FileStorage.trans_with_client.TransWithWithClientImpl;
import properties.StorageNode;

import java.io.IOException;

/**
 * 服务器主程序，启动对客户端和存储节点的监听
 * Created by Sunny on 2017/7/6 0006.
 */
public class FileStorage {
    public static void main(String[] args) throws IOException {
//        //初始化节点信息
//        StorageNode.init(args[0]);

        StorageNode.init(StorageNode.propertiesBasePath + "storage4.properties");
//        FileStorageInfo.getInstance().setLeftCapacity(50);

        //启动与服务器的联系，定期发送心跳包
        new CommunicateWithServerThreadSupport(new CommunicateWithServerStrategyImpl(),StorageNode.fileServerIp, StorageNode.fileServerPort);

        //启动与FileServer交互的程序，并监听来自FileServer的请求
        new TransWithClientServer(StorageNode.nodePort, new TransWithClientThreadSupport(new TransWithWithClientImpl()));


    }
}
