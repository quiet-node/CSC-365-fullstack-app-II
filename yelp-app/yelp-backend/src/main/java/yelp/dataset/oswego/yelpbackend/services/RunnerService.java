package yelp.dataset.oswego.yelpbackend.services;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import yelp.dataset.oswego.yelpbackend.dataStructure.btree.BusinessBtree;
import yelp.dataset.oswego.yelpbackend.models.BusinessModel;

@Component // this + CommandLineRunner are used to run code at application startup -like useEffect in React
public class RunnerService implements CommandLineRunner{
    @Override
    public void run(String... args) throws Exception {
        final String BASEURI = System.getProperty("user.dir");
        BusinessBtree btree = new JsonService().jsonParser(BASEURI + "/yelp-app/yelp-dataset/business.json");
        List<BusinessModel> businessList = new JsonService().jsonParser2(BASEURI + "/yelp-app/yelp-dataset/business.json");
        
        System.out.println("root: " +btree.getRoot());
        
        BusinessModel model = btree.getRoot().getBChild().get(0).getBKeys().get(0);
        System.out.println("model: " +model);

        for (int i = 0; i < businessList.size(); i++) {
            BusinessModel model3 = businessList.get(i);
            BusinessModel model2 =  btree.getRoot().searchKey(model3);
            System.out.println("model " +i+ ": " +model2);
        }

    }
}

