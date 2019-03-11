package utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Sunny on 2017/4/27 0027.
 */
public class MyThreadPool {
    private static MyThreadPool instance;
    private ExecutorService executorService;
    private static final Object lock = new byte[1];

    public MyThreadPool(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public static MyThreadPool getInstance() {
        if (instance == null || instance.executorService.isShutdown()) {
            synchronized(lock){     //创建的时候使用同步锁，防止多个并发时重复创建
                instance = new MyThreadPool(Executors.newCachedThreadPool());
            }
        }
        return instance;
    }

    public void submit(Runnable task) {
        instance.executorService.submit(task);
    }

    public void shutDown(){
        if(instance != null){
            executorService.shutdown();
            instance = null;
        }
    }

    public void shutDownNow(){
        if(instance != null){
            executorService.shutdownNow();
            instance = null;
        }
    }

}
