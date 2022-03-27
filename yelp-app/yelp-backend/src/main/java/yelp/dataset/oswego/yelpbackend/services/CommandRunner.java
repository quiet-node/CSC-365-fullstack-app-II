package yelp.dataset.oswego.yelpbackend.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import yelp.dataset.oswego.yelpbackend.algorithms.btree.BusinessBtree;


@Component // this + CommandLineRunner are used to run code at application startup -like useEffect in React
public class CommandRunner implements CommandLineRunner{

    @Override
    public void run(String... args) throws Exception {
        BusinessBtree businessBtree = new IOUtil().readBtree();
        businessBtree.traverse();
    }
}

