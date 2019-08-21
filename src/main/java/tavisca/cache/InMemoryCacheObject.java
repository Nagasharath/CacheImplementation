package tavisca.cache;

public class InMemoryCacheObject {

    private Object value;               // value of the object

    private long validForTime;          // in milliseconds , validity time of the object ex: 100000 milliseconds

    private long expiryTime;            // in milliseconds , total time from the current system time i,e system_time + validForTime

    public InMemoryCacheObject() {
    }

    public InMemoryCacheObject(Object value, long validForTime, long expiryTime) {
        this.value = value;
        this.validForTime = validForTime;
        this.expiryTime = expiryTime;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public long getValidForTime() {
        return validForTime;
    }

    public void setValidForTime(long validForTime) {
        this.validForTime = validForTime;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public boolean ifCacheObjectExpired(){
        if(this.expiryTime < System.currentTimeMillis())
            return true;
        return false;
    }

}
