package tavisca.utils;

import tavisca.cache.InMemoryCacheObject;
import tavisca.projectConstants.ProjectConstants;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;
public class DaemonThread implements Runnable {

    ConcurrentHashMap<String , SoftReference<InMemoryCacheObject>> inMemoryCache;
    public DaemonThread(ConcurrentHashMap<String , SoftReference<InMemoryCacheObject>> inMemoryCache){
            this.inMemoryCache = inMemoryCache;
    }

    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            try {
                Thread.currentThread().sleep(ProjectConstants.CLEAN_UP_TIME_FOR_CACHE_OBJECT);
                inMemoryCache.entrySet().removeIf(entry ->
                        entry.getValue().get().getExpiryTime() < System.currentTimeMillis()
                );
            }catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }
}
