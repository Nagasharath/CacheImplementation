package tavisca.interfaceimpl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tavisca.cache.InMemoryCacheObject;
import tavisca.interfaces.CacheFunctionalities;
import tavisca.projectConstants.ProjectConstants;
import tavisca.utils.DaemonThread;

import java.lang.ref.SoftReference;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


public class CacheFunctionalitiesImpl implements CacheFunctionalities {

    private ConcurrentHashMap<String , SoftReference<InMemoryCacheObject>> inMemoryCache = new ConcurrentHashMap<String, SoftReference<InMemoryCacheObject>>(ProjectConstants.SIZE_OF_IN_MEMORY_CACHE);

    private static final Logger logger = LoggerFactory.getLogger(CacheFunctionalitiesImpl.class);

    public CacheFunctionalitiesImpl() throws InterruptedException {

        DaemonThread daemonThread = new DaemonThread(inMemoryCache);
        Thread thread = new Thread(daemonThread);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void add(String key, Object value, long periodInMilliSeconds) {

        if(key == null || key.isEmpty()){
            logger.info("Key can not be null or empty");
            return;
        }
        if(value == null || value.toString().isEmpty()){

            inMemoryCache.remove(key);
            logger.info("object is removed from the cache");
        }else{

            if(inMemoryCache.size()<ProjectConstants.SIZE_OF_IN_MEMORY_CACHE) {
                InMemoryCacheObject inMemoryCacheObject= Optional.ofNullable(inMemoryCache.get(key)).map(inMemoryCacheObjectSoftReference -> inMemoryCacheObjectSoftReference.get()).orElse(null);
                if(inMemoryCacheObject!=null) {
                    inMemoryCacheObject.setExpiryTime(System.currentTimeMillis() + periodInMilliSeconds);
                    inMemoryCacheObject.setValue(value);
                    inMemoryCacheObject.setValidForTime(periodInMilliSeconds);
                    inMemoryCache.put(key,new SoftReference<>(inMemoryCacheObject));
                    logger.info("Object exists in cache,so it is updated with the new values");
                }
                else {
                    long expiryTime = System.currentTimeMillis() + periodInMilliSeconds;
                    inMemoryCache.put(key, new SoftReference<>(new InMemoryCacheObject(value, periodInMilliSeconds, expiryTime)));
                    logger.info("object added in cache");
                }
            }else{
                logger.info("Cache is full for now");
            }
        }

    }

    @Override
    public Object get(String key) {

         boolean isAvailable =  Optional.ofNullable(inMemoryCache.get(key))
                                  .map(inMemoryCacheObjectSoftReference -> !inMemoryCacheObjectSoftReference.get().ifCacheObjectExpired())
                                  .orElse(false);

        InMemoryCacheObject cachedObject = isAvailable ? inMemoryCache.get(key).get():null;

        if(cachedObject!=null){
            cachedObject.setExpiryTime(System.currentTimeMillis()+cachedObject.getValidForTime());
        }
        logger.info("object access time updated");
        return cachedObject==null?null:cachedObject.getValue();

    }

    @Override
    public void remove(String key) {
        inMemoryCache.entrySet().removeIf(entry -> entry.getKey().equals(key));
        logger.info("If key is available it is removed");
    }

    @Override
    public void clear() {
        inMemoryCache.clear();
        logger.info("Cache is cleared");
    }

    @Override
    public long size() {
        logger.info("cache size:"+inMemoryCache.size());
        return inMemoryCache.size();
    }
}
