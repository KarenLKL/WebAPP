package com.newbee.summary.util;


import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by kl09 on 2017/4/13.
 */
public class ThreadExecutorSingleton {
    private static ThreadPoolExecutor instance=null;
    private ThreadExecutorSingleton(){

    }

    public static synchronized ThreadPoolExecutor getInstance(){
        if (instance==null){
            ThreadFactory threadFactory= Executors.defaultThreadFactory();
            instance = (ThreadPoolExecutor) Executors.newCachedThreadPool(threadFactory);
            instance.setCorePoolSize(2);
            instance.setMaximumPoolSize(10);
        }
        return instance;
    }
}
