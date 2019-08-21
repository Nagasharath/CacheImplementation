package tavisca.interfaces;

public interface CacheFunctionalities {
    /*
    *        Add the object into cache.
    *        Params: Key  - Unique identifier for the object.
    *                       If same key is used for another object , existing object will be updated
    *               value - object
    *               periodInMillisSeconds  -  validity time of the object
    * */
    public void add(String key, Object value, long periodInMilliSeconds);


    /*
    *       get the object from the cache using key.Key here is unique identifier for that object
    *
    * */
    public Object get(String key);

    /*
     *       remove the object from the cache using key.Key here is unique identifier for that object
     *
     * */
    public void remove(String key);

    /*
     *       Clears the whole cache
     *
     * */
    public void clear();

    /*
     *       get the size of the current object.It will be changed as per the time specified in Project Constants.
     *
     * */
    public long size();

}
