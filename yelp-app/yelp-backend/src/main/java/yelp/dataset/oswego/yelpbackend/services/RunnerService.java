package yelp.dataset.oswego.yelpbackend.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import yelp.dataset.oswego.yelpbackend.dataStructure.btree.BusinessBtree;

@Component // this + CommandLineRunner are used to run code at application startup -like useEffect in React
public class RunnerService implements CommandLineRunner{
    @Override
    public void run(String... args) throws Exception {
        BusinessBtree btree = new JsonService().jsonParser("/Users/logan/coding/SUNY_Oswego/CSC-365/In_Class/Assignment2/yelp-app/yelp-dataset/businessSmall.json");
        System.out.println("root: " +btree.getRoot());
        
        // BusinessModel model = btree.getRoot().getBChild().get(0).getBKeys().get(0);
        // System.out.println("model: " +model);

        // List<BusinessModel> businessList = new JsonService().jsonParser2("/Users/logan/coding/SUNY_Oswego/CSC-365/In_Class/Assignment2/yelp-app/yelp-dataset/businessSmall.json");
        // BusinessModel model3 = businessList.get(6);
        // System.out.println("model3: " + model3);

        // BusinessBNode model2 =  btree.findNode(model3);
        // System.out.println("model2: " +model2);

    }
}

