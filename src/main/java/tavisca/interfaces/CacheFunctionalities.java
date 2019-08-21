package tavisca.interfaces;

public interface CacheFunctionalities {

    public void add(String key, Object value, long periodInMilliSeconds);

    public Object get(String key);

    public void remove(String key);

    public void clear();

    public long size();

}
