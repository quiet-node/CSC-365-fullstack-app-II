package yelp.dataset.oswego.yelpbackend.services;
import java.util.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import yelp.dataset.oswego.yelpbackend.algorithms.clustering.Centroid;
import yelp.dataset.oswego.yelpbackend.algorithms.clustering.KMeans;
import yelp.dataset.oswego.yelpbackend.dataStructure.btree.BusinessBtree;
import yelp.dataset.oswego.yelpbackend.models.BusinessModel;

@Component // this + CommandLineRunner are used to run code at application startup -like useEffect in React
public class RunnerService implements CommandLineRunner{
    @Override
    public void run(String... args) throws Exception {
        // new IOService().writeBtree(new JsonService().initBusinessBtree("/Users/logan/coding/SUNY_Oswego/CSC-365/In_Class/Assignment2/yelp-app/yelp-dataset/business.json"));

        BusinessBtree businessBtree = new IOService().readBtree();
        Map<Centroid, List<BusinessModel>> clusters = new KMeans().initializeClusers(businessBtree, 7);


    }
}

