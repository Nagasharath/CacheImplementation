package tavisca;


import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tavisca.interfaceimpl.CacheFunctionalitiesImpl;
import tavisca.interfaces.CacheFunctionalities;
import tavisca.pojos.Client;
import tavisca.pojos.Company;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        BasicConfigurator.configure();

        Logger logger = LoggerFactory.getLogger(Main.class.getName());

        /*
        * CacheFunctionalities is the interface for the operation on the cache.
        * */
        CacheFunctionalities cacheFunctionalities = new CacheFunctionalitiesImpl();

        /*
         * adding data into the cache
         * */
        cacheFunctionalities.add("client_1",new Client("XYZ Pvt Ltd","Pune","xyz project"),7000);

        cacheFunctionalities.add("client_2",new Client("ABC Pvt Ltd","Hyderabad","abc project"),7000);

        cacheFunctionalities.add("company_1",new Company("Company_1","Pune",1000,"Company_1_CEO"),8000);

        cacheFunctionalities.add("company_3",new Company("Company_2","Pune",500,"Company_1_CEO"),6000);

        cacheFunctionalities.add("client_3",new Client("XYZ Pvt Ltd","Pune","xyz project"),1000);

        /*
        * get the data of client using key.Once the object from the cache is used ,its validity time will be updated.
        * */
        Client client2 = (Client)cacheFunctionalities.get("client_2");

        logger.info(client2.getName()+" "+client2.getLocation()+" "+client2.getProjectName());

        logger.info("Size of cache:"+cacheFunctionalities.size());

        /*
         * sleep the thread for 5 seconds and check the size of the cache.
         * */
        Thread.currentThread().sleep(5000);

        logger.info("After some time size of cache:"+cacheFunctionalities.size());

        /*
         * add new object into the cache
         * */
        cacheFunctionalities.add("client_4",new Client("XYZ Pvt Ltd","Pune","xyz project"),1000);

        Client client4 = (Client)cacheFunctionalities.get("client_4");

        logger.info("added new object:"+client4.getName()+" "+client4.getLocation()+" "+client4.getProjectName());
    }
}
