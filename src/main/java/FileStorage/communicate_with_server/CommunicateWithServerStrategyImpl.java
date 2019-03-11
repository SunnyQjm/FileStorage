package FileStorage.communicate_with_server;

import FileStorage.Data.FileStorageInfo;
import FileStorage.Data.LinkedBlockingQueueManager;
import FileStorage.Data.TransProtocol;
import FileStorage.body.RequestBody;
import properties.StorageNode;
import utils.CheckSumUtil;
import utils.GsonUtil;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.zip.CRC32;

/**
 * 该类是节点服务器与服务器通信策略的一个实现类
 * Created by Sunny on 2017/7/7 0007.
 */
public class CommunicateWithServerStrategyImpl implements CommunicateWithServerStrategy {

    @Override
    public void registerOrUpdate(String ip, int port) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();

        RequestBody<FileStorageInfo> body = new RequestBody<>(TransProtocol.CODE_UPDATE_STORAGE_INFO, FileStorageInfo.getInstance());
        String uuid = LinkedBlockingQueueManager.getInstance().poll();
        String info = GsonUtil.getInstance().toJson(body);
        if(uuid == null){
            info += "####" + 0;
        }else{
            info += "####" + uuid;
        }

        //准备数据，并计算出32bit的CRC校验码放在数据末尾
        byte[] data = info.getBytes(StandardCharsets.UTF_8);
        byte[] dataWithCRC = Arrays.copyOf(data, data.length + 4);
        byte[] checkSum = CheckSumUtil.getCRC32Value(data);
        System.arraycopy(checkSum, 4, dataWithCRC, data.length, dataWithCRC.length - data.length);

        //开始发送数据包，不管服务器在不在线，只管发
        DatagramPacket datagramPacket = new DatagramPacket(dataWithCRC, dataWithCRC.length, new InetSocketAddress(StorageNode.fileServerIp, StorageNode.fileServerPort));
        datagramSocket.send(datagramPacket);
        System.out.println("send : " + info);
        datagramSocket.close();
    }

}
