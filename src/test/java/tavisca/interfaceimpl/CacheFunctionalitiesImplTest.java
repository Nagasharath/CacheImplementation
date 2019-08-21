package tavisca.interfaceimpl;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tavisca.cache.InMemoryCacheObject;
import tavisca.interfaces.CacheFunctionalities;
import tavisca.pojos.Client;
import tavisca.pojos.Company;
import tavisca.projectConstants.ProjectConstants;

import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class CacheFunctionalitiesImplTest {

    CacheFunctionalities cacheFunctionalities;
    private final int mapSize = ProjectConstants.SIZE_OF_IN_MEMORY_CACHE;
@BeforeEach
        public void instantiateMapWithData() throws InterruptedException{

            ConcurrentHashMap<String, SoftReference<InMemoryCacheObject>> concurrentHashMap = new ConcurrentHashMap<>(mapSize);

            Client client_1 = new Client("IBM","Pune","IBM_Project");

            Client client_2 = new Client("Tavisca","Pune","Tavisca_Project");

            Client client_3 = new Client("Coditas","Pune","Coditas_Project");

            Company company_1 = new Company("IBM","Pune",1000,"CEO_IBM");

            Company company_2 = new Company("Tavisca","Pune",5000,"CEO_Tavisca");

            cacheFunctionalities = new CacheFunctionalitiesImpl();
            cacheFunctionalities.add("client_1",client_1,5000);
            cacheFunctionalities.add("client_2",client_2,50000);
            cacheFunctionalities.add("company_1",company_1,7000);
            cacheFunctionalities.add("client_3",client_3,7000);
            cacheFunctionalities.add("company_2",company_2,2000);
}
    @Test
    public void add() throws InterruptedException{


        assertEquals(mapSize,cacheFunctionalities.size());

        Company company_3 = new Company("Coditas","Pune",2000,"CEO_coditas");

        Thread.currentThread().sleep(6000);

        cacheFunctionalities.add("company_3",company_3,4000);

        assertNotEquals(mapSize,cacheFunctionalities.size());

        Thread.currentThread().sleep(4000);


    }

    @Test
    public void remove(){
        cacheFunctionalities.remove("company_3");

        assertEquals(mapSize,cacheFunctionalities.size());

        cacheFunctionalities.remove("company_2");

        assertNotEquals(mapSize,cacheFunctionalities.size());
    }

    @Test
    public void clear(){

        assertEquals(mapSize,cacheFunctionalities.size());

        cacheFunctionalities.clear();

        assertNotEquals(mapSize,cacheFunctionalities.size());

    }

    @Test
    public void size(){

    long size = cacheFunctionalities.size();

    assertEquals(mapSize,size);

    }

    @Test
    public  void get(){

        Object oldObject = cacheFunctionalities.get("client_2");
        Object updatedObjectWithTime = cacheFunctionalities.get("client_2");

        assertEquals(((Client)oldObject).getName(),((Client)updatedObjectWithTime).getName());
        assertEquals(((Client)oldObject).getLocation(),((Client)updatedObjectWithTime).getLocation());
        assertEquals(((Client)oldObject).getProjectName(),((Client)updatedObjectWithTime).getProjectName());

        Client client_4 = new Client("Tavisca","Hyderabad","Tavisca_Project");
        cacheFunctionalities.add("client_2",client_4,6000);
        Object updatedObjectWithValues = cacheFunctionalities.get("client_2");


        assertEquals(((Client)oldObject).getName(),((Client)updatedObjectWithValues).getName());
        assertEquals(((Client)oldObject).getLocation(),((Client)updatedObjectWithValues).getLocation());
        assertEquals(((Client)oldObject).getProjectName(),((Client)updatedObjectWithValues).getProjectName());


    }


}
