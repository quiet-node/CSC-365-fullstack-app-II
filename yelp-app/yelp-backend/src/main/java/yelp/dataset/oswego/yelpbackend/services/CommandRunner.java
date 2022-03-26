package yelp.dataset.oswego.yelpbackend.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import yelp.dataset.oswego.yelpbackend.repositories.BusinessBtree;

@Component // this + CommandLineRunner are used to run code at application startup -like useEffect in React
public class CommandRunner implements CommandLineRunner{

    @Override
    public void run(String... args) throws Exception {
        BusinessBtree bTree = new BusinessBtree(3);
        bTree.insert(5);
        bTree.insert(10);
        bTree.insert(15);
        bTree.insert(20);
        bTree.insert(25);
        bTree.insert(11);
        bTree.insert(12);
        bTree.insert(13);
        bTree.insert(14);
        bTree.insert(23);
        bTree.insert(24);

        bTree.traverse();
        System.out.println("root: " + bTree.getRoot());
        System.out.println(bTree.search(14));

    }
}

