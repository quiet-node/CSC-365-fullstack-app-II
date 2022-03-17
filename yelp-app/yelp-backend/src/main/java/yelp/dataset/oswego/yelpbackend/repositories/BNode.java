package yelp.dataset.oswego.yelpbackend.repositories;

import java.util.ArrayList;

import yelp.dataset.oswego.yelpbackend.models.BusinessModel;

public class BNode {
    private int childrenNum;        // number of children
    private ArrayList<BusinessModel> BKeys;  // Array of business key
    boolean isLeaf; // true ? leaf : not leaf

    public BNode(int M) {
        childrenNum = 0;
        BKeys = new ArrayList<BusinessModel>(2 * M -1); // each BNode contains at most 2*M -1 keys
    }
    
}
