package com.fit2cloud.java.shell.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.*;

/**
 * Description:符合阿里巴巴规范的线程池
 * User: zhouzhou
 * Date: 2019-01-15
 * Time: 14:19
 */
public class ThreadPoolUtil {

    public static ThreadPoolExecutor threadPool;

    private static int corePoolSize = 10;

    private static int maximumPoolSize = 10;

    private static long keepAliveTime = 600;

    private static int capacity = 50;


    @PreDestroy
    public static void shutdown() {
        if (threadPool != null) {
            threadPool.shutdown();
        }
    }

    /**
     * 无返回值直接执行
     *
     * @param runnable
     */
    public static void execute(Runnable runnable) {
        getThreadPool().execute(runnable);
    }

    /**
     * 返回值直接执行
     *
     * @param callable
     */
    public static <T> Future<T> submit(Callable<T> callable) {
        return getThreadPool().submit(callable);
    }


    /**
     * dcs获取线程池
     *
     * @return 线程池对象
     */
    public static ThreadPoolExecutor getThreadPool() {
        if (threadPool != null) {
            return threadPool;
        } else {
            synchronized (ThreadPoolUtil.class) {
                if (threadPool == null) {
                    threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                            new LinkedBlockingQueue<>(capacity), new ThreadPoolExecutor.CallerRunsPolicy());
                }
                return threadPool;
            }
        }
    }

}