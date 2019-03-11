package FileStorage.trans_with_client;

import utils.MyThreadPool;

import java.net.Socket;

/**
 * Created by Sunny on 2017/7/6 0006.
 */
public class TransWithClientThreadSupport implements TransWithClientStrategy {
    private TransWithClientStrategy fcs;

    public TransWithClientThreadSupport(TransWithClientStrategy fcs) {
        this.fcs = fcs;
    }

    @Override
    public void service(Socket socket) {
        //将处理socket的工作交给线程池，让线程池处理
        MyThreadPool.getInstance().submit(new TransWithClientRunnable(socket, fcs));
    }
}
