package yelp.dataset.oswego.yelpbackend.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import yelp.dataset.oswego.yelpbackend.dataStructure.btree.BusinessBtree;




@Component // this + CommandLineRunner are used to run code at application startup -like useEffect in React
public class RunnerService implements CommandLineRunner{

    @Override
    public void run(String... args) throws Exception {
        BusinessBtree businessBtree = new IOService().readBtree();
        new IOService().writeNode(businessBtree);
        new IOService().readNode();
    }
}

