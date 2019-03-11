package FileStorage.trans_with_client;

import FileStorage.Data.FileStorageInfo;

import java.io.IOException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

/**
 *
 * Created by Sunny on 2017/7/6 0006.
 */
public class TransWithClientRunnable implements Runnable {

    private Socket socket;
    private TransWithClientStrategy fcs;

    public TransWithClientRunnable(Socket socket, TransWithClientStrategy fcs) {
        this.socket = socket;
        this.fcs = fcs;
    }

    @Override
    public void run() {
        try {
            fcs.service(socket);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            FileStorageInfo.getInstance().subConnectNum(1);
        }
    }
}
