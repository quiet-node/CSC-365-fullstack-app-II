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
        BusinessBtree btree = new JsonService().jsonParser("/Users/logan/coding/SUNY_Oswego/CSC-365/In_Class/Assignment2/yelp-app/yelp-dataset/business.json");
        List<BusinessModel> allKeys = btree.retrieveAllKeys();
        System.out.println(allKeys);

        // BusinessBtree businessBtree = new IOService().readBtree();
        // new IOService().writeNode(businessBtree);
        // new IOService().readNode();
    }
}

