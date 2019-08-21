# CacheImplementation

<b>Internal cache memory implementation using core Java</b>
<br>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Caching is storing the data into the memory to avoid the frequent access to the database or the main memory.In the given problem statement,I have used concurrent hashmap as it is a thread safe.Daemon thread is implemented to check regularly if there is any object that is not used frequently and whose validity time is over(object will be there in the memory for particular time).Cache will have the limited size.Once the size is over no objects can be added to that cache unless the object is freed or its validity time is over.</p>

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I have implemented the functionalities like add object into the cache,get the object ,remove object,clear cache and the size of the cache.</p>
   
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I have given the size of the cache as 5 objects and the refrest rate of the cache as 5 seconds.It can be changed in the application in ProjectConstant class.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;There are two pojo classes: client and the Company .These two classes are added as a object into the cache with each object having period of its validity from the current system time.</p>

For each operation , log message is displayed.

**For the given input :** <br>

    cacheFunctionalities.add("client_1",new Client("XYZ Pvt Ltd","Pune","xyz project"),7000);

    cacheFunctionalities.add("client_2",new Client("ABC Pvt Ltd","Hyderabad","abc project"),7000);

    cacheFunctionalities.add("company_1",new Company("Company_1","Pune",1000,"Company_1_CEO"),8000);

    cacheFunctionalities.add("company_2",new Company("Company_2","Pune",500,"Company_2_CEO"),6000);

    cacheFunctionalities.add("client_3",new Client("XYZ Pvt Ltd","Pune","xyz project"),1000);
    
    cacheFunctionalities.add("company_2",new Company("Company_2","Pune",1500,"Company_2_CEO"),6000);

**Output is :**<br>

    object added in cache
    object added in cache
    object added in cache
    object added in cache
    object added in cache
    object access time updated
    
    
