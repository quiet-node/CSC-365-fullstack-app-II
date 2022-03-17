package yelp.dataset.oswego.yelpbackend.repositories;

import java.io.Serializable;

public class BusinessBtree implements Serializable{
    /* 
        - POJO will be wiped off by garbge collector after a running session
        - Needs to store on disk for disk => transfrom POJO into byte stream
        - java.io.Serializable does such job
    */

    private static final int M = 4; // max childrend per B-tree node = M - 1 
    private BNode root;
    private int height;
    private int nodeCounts; //  number of BNodes 


}
