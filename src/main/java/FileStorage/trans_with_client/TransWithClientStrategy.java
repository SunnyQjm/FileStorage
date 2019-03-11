package FileStorage.trans_with_client;

import java.io.IOException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

/**
 * 该策略用于应对客户端上传下载文件或服务器发送的删除文件的请求
 * Created by Sunny on 2017/7/6 0006.
 */
public interface TransWithClientStrategy {
    public void service(Socket socket) throws IOException, NoSuchAlgorithmException;
}
